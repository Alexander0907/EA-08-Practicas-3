
package com.practica1.service.impl;

import com.google.api.services.storage.Storage;
import com.google.auth.Credentials;
import com.google.auth.ServiceAccountSigner;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage.SignUrlOption;
import com.google.cloud.storage.StorageOptions;
import com.practica1.service.firebaseStorageService;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.concurrent.TimeUnit;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class firebaseStorageServiceImpl implements firebaseStorageService {

    // Ajusta estas variables según tu configuración
    private static final String rutaJsonFile = "ruta/al/json"; // Ruta donde está el archivo JSON
    private static final String archivoJsonFile = "archivo.json"; // Nombre del archivo JSON
    private static final String bucketName = "mi-bucket"; // Nombre de tu bucket en Firebase
    private static final String rutaSuperiorStorage = "ruta/superior"; // Carpeta superior en el bucket (opcional)

    @Override
    public String cargaImagen(MultipartFile archivoLocalCliente, String carpeta, Long id) {
        try {
            // Obtiene la extensión del archivo
            String extension = archivoLocalCliente.getOriginalFilename();
            
            // Genera el nombre del archivo en función del ID
            String fileName = "img" + id + extension;

            // Convierte el archivo a un archivo temporal
            File file = this.convertToFile(archivoLocalCliente);

            // Sube el archivo a Firebase Storage y obtiene el URL
            String URL = this.uploadFile(file, carpeta, fileName);

            // Elimina el archivo temporal
            file.delete();

            return URL;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

 private String uploadFile(File file, String carpeta, String fileName) throws IOException {
    // Accede al archivo JSON de credenciales
    ClassPathResource json = new ClassPathResource(rutaJsonFile + File.separator + archivoJsonFile);
    
    // Define la ubicación en el bucket de Google Cloud Storage
    BlobId blobId = BlobId.of(bucketName, rutaSuperiorStorage + "/" + carpeta + "/" + fileName);
    BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("media").build();
    
    // Carga las credenciales desde el archivo JSON
    GoogleCredentials credentials = GoogleCredentials.fromStream(json.getInputStream());
    com.google.cloud.storage.Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
    
    // Sube el archivo a Firebase Storage
    storage.create(blobInfo, Files.readAllBytes(file.toPath()));
    
    // Genera una URL firmada válida por 10 años (3650 días)
    return storage.signUrl(blobInfo, 3650, TimeUnit.DAYS, SignUrlOption.signWith((ServiceAccountSigner) credentials)).toString();
}


    // Convierte el archivo de MultipartFile a un archivo temporal
    private File convertToFile(MultipartFile archivoLocalCliente) throws IOException {
        File tempFile = File.createTempFile("img", null);
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            fos.write(archivoLocalCliente.getBytes());
        }
        return tempFile;
    }
}
