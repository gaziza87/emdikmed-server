package kz.gaziza.emdikmed.service.docs;


import org.springframework.data.mongodb.gridfs.GridFsResource;

import java.io.IOException;
import java.io.InputStream;

public interface IMongoDocService {
   GridFsResource downloadScanDoc(String fileId) throws IOException;

   String saveScanDoc(InputStream content, String mimeType, String fileName) throws IOException;
   void deleteScanDoc(String id);
}
