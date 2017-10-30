package resourse;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author yehwalashetbezuneh
 */
@Controller
@EnableAutoConfiguration
public class uploadFiles {
        public static final String UPLOADED_FOLDER = System.getProperty("UPLOADED_FOLDER");
    @RequestMapping("/")
    public String index(){
        return "upload";
    }
    @RequestMapping("/upload")
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile upload){

        try{
  //          saveFiles(Arrays.asList(upload));
            String content = new String(upload.getBytes());
            return "file name:"+ upload.getOriginalFilename() + "<br> content:" + content;
            }catch (Exception e){
            e.printStackTrace();
            }
        return "file name: " + upload.getOriginalFilename()+"<br> read file content error. ";
        
        }
      public static void main(String[] args) {
       
        SpringApplication.run(uploadFiles.class, args);
    }

    private void saveFiles(List<MultipartFile> files) throws IOException {
    for (MultipartFile file : files) {

            if (file.isEmpty()) {
                continue; //next pls
            }

            byte[] bytes = null;
        try {
            bytes = file.getBytes();
        } catch (IOException ex) {
            Logger.getLogger(uploadFiles.class.getName()).log(Level.SEVERE, null, ex);
        }
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);

        }

    }
}
