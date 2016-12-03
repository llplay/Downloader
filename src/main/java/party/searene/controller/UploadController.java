package party.searene.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import party.searene.client.XRClient;

import java.io.*;
import java.nio.file.Paths;

/**
 * Created by searene on 11/27/16.
 */
@Controller
public class UploadController {

    private Logger logger = LogManager.getLogger(this.getClass());

    private String title = "Downloader";

    @Autowired
    private XRClient xrClient;

    @Value("${upload.directory}")
    private String uploadDirectory;


    /**
     * POST /uploadFile -> receive and locally save a file.
     *
     * @param uploadfile The uploaded file as Multipart file parameter in the
     * HTTP request. The RequestParam name must be the same of the attribute
     * "name" in the input tag with type file.
     *
     * @return An http OK status in case of success, an http 4xx status in case
     * of errors.
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> uploadFile(
            @RequestParam("uploadfile") MultipartFile uploadfile) {

        try {
            // Get the filename and build the local file path
            String filename = uploadfile.getOriginalFilename();
//            String filepath = Paths.get(uploadDirectory, filename).toString();
            String filepath = Paths.get(uploadDirectory, filename).toString();

            // Save the file locally
            BufferedOutputStream stream =
                    new BufferedOutputStream(new FileOutputStream(new File(filepath)));

            logger.debug(String.format("Creating file %s", filepath));
            stream.write(uploadfile.getBytes());
            stream.close();
            File file = new File(filepath);
            xrClient.AddByFile(new File(filepath));
        }
        catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Get /search/activeTask -> search active task from rtorrent
     * @return An response entity contains active task list
     */
    @RequestMapping(value = "/search/activeTask", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> getActiveTask(){
        return null;
    }

    /**
     * Get /search/allTask -> search all task from rtorrent
     * @return An response entity contains all task list
     */
    @RequestMapping(value = "/search/allTask", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> getAllTask(){
        Object[] list = xrClient.getTaskList();
        // put list into return ??
        return null;
    }

    /**
     * Get /pauseTask -> pause task
     * @param taskId taskId is the Id of rtorrent task. The
     *               RequestParam name must be the same of
     *               the attribute "name" in the input tag with type file.
     * @return An http OK status in case of success, an http 4xx status in case
     * of errors
     */
    @RequestMapping(value = "/pauseTask", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> pauseTask(
            @RequestParam("taskId") String taskId){
        Object result = xrClient.Pause(taskId);
        // judge result if true or false
        return null;
    }

    /**
     * Get /resumeTask -> resume task
     * @param taskId taskId is the Id of rtorrent task. The
     *               RequestParam name must be the same of
     *               the attribute "name" in the input tag with type file.
     * @return An http OK status in case of success, an http 4xx status in case
     * of errors
     */
    @RequestMapping(value = "/resumeTask", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> resumeTask(
            @RequestParam("taskId") String taskId){
        Object result = xrClient.Resum(taskId);
        // judge result if true or false
        return null;
    }
}