package toolkit;

import java.io.File;
import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import java.util.*;

public class UploadTool {
    private int buffersize = 4096;
    private int SizeMax = 1024 * 1024;// 1Mb
    private File tempfile = null;
    private String def_upload_dir = null;

    // keep parameter
    private Map map = null;
    private Map uploadlist = null;

    // store all request parameters into map
    public UploadTool(HttpServletRequest request) throws FileUploadException,
            UnsupportedEncodingException {

        map = new HashMap();
        uploadlist = new HashMap();

        // create a disk-base object
        DiskFileItemFactory factory = new DiskFileItemFactory();

        // initial
        // set buffer size
        factory.setSizeThreshold(buffersize);
        // The directory in which temporary files will be located.

        factory.setRepository(tempfile);

        // create upload object
        ServletFileUpload upload = new ServletFileUpload(factory);

        // set max size
        upload.setSizeMax(SizeMax * 10);

        // each Fileitem is an object from form eg, input type="text"
        List items = null; // cause FileUploadException
        // retrieve data from request
        items = upload.parseRequest(request); // Parse the request

        Iterator iter = items.iterator();

        while (iter.hasNext()) {// get parameters instead of write to file
            FileItem item = (FileItem) iter.next();
            // text field
            if (item.isFormField()) {
                map.put(item.getFieldName(), item.getString("Big5"));
            } else {// file field
                // or it's a file upload request
                if (item.getSize() > 0) {
                    uploadlist.put(item.getFieldName(), item);
                }
            }
        }
    }

    // set upload directory
    public void setUploadDir(String upload_dir) {
        this.def_upload_dir = upload_dir;
    }

    public Map getAllParameter() {
        Map rvalue = new HashMap();
        rvalue.putAll(map);
        rvalue.putAll(uploadlist);
        return rvalue;
    }

    public String getParameter(String FieldName) {
        if (map.containsKey(FieldName))
            return String.valueOf(map.get(FieldName));
        else
            return null;
    }

    public FileItem getUploadParameter(String FieldName) {
        if (uploadlist.containsKey(FieldName))
            return (FileItem) uploadlist.get(FieldName);
        else
            return null;
    }

    public String checkUpload() {
        Iterator iter = uploadlist.keySet().iterator();
        while (iter.hasNext()) {
            Object Name = iter.next();
            FileItem item = (FileItem) uploadlist.get(Name);
            String itename = item.getName();
            if (item.getSize() > SizeMax)
            return "Too large!";
        }
        return "";
    }

    public String doUpload(FileItem item, String fileName) {
        String str = "";
        long sizeInBytes = item.getSize();
        // check file size
        if (sizeInBytes > SizeMax)
        return "Too large!";

        if (sizeInBytes > 0) {

            int index = -1;
            String itename = null;
            if ((index = item.getName().lastIndexOf("\\")) != -1) {
                itename = item.getName().substring(index,
                        item.getName().length());
            }else {
                itename = item.getName();
            }
            // extension
            String formatName = itename.substring(itename.length() - 4, itename.length());
            fileName = (fileName + formatName).toLowerCase();
            //System.out.println("Filename:" + fileName);
            File uploadedFile = new File(def_upload_dir + fileName);
            // Exception
            try {
                item.write(uploadedFile);

            } catch (Exception e) {
                System.out.println("Failed!" + e.toString());
                str = "Failed!";
            }

        }
        return str;
    }

    public boolean isExtUpload(String fileName) {
        return uploadlist.containsKey(fileName);
    }
}