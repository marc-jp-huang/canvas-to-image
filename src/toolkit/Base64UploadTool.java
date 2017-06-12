package toolkit;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.StringWriter;

/**
 * Created by Marc on 2017/6/12.
 */
public class Base64UploadTool {

    public Base64UploadTool(HttpServletRequest request,String upload_dir) throws Exception {
        InputStream in = null;
        FileOutputStream fos = null;
        try {
            HttpServletRequestWrapper wrappedRequest = new HttpServletRequestWrapper(request);
            InputStream is = wrappedRequest.getInputStream();
            StringWriter writer = new StringWriter();
            IOUtils.copy(is, writer, "UTF-8");
            String imageString = writer.toString();
            imageString = imageString.substring("data:image/png;base64,"
                    .length());
            byte[] contentData = imageString.getBytes();
            byte[] decodedData = Base64.decodeBase64(contentData);
            String imgName = upload_dir
                    + String.valueOf(System.currentTimeMillis()) + ".png";
            fos = new FileOutputStream(imgName);
            fos.write(decodedData);
        } catch (Exception e) {
            e.printStackTrace();
            String loggerMessage = "Upload image failed : ";
        } finally {
            if (in != null) {
                in.close();
            }
            if (fos != null) {
                fos.close();
            }
        }
    }
}
