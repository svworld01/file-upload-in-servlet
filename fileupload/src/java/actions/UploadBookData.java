package actions;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import java.util.*;

public class UploadBookData extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String img = "";
        //get data from request
        LinkedHashMap<String, String> list = new LinkedHashMap<String, String>();

        //writing file
        try {
            ServletFileUpload sf = new ServletFileUpload(new DiskFileItemFactory());
            List<FileItem> multifiles = sf.parseRequest(request);
            for (FileItem item : multifiles) {

                if (item.isFormField()) {
                    // Process regular form field (input type="text|radio|checkbox|etc", select, etc).
                    list.put(item.getFieldName(), item.getString());
                }else{
                    item.write(new File("E:/j2ee/projects/fileupload/web/Uploaded/" + item.getName()));
                    img = item.getName();
                }
                
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
       
        //create connection and insert
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "");
            String sql = "INSERT INTO `books`(`book_id`, `name`, `price`, `offer`, `img`) VALUES(?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, list.get("book_id"));
            ps.setString(2, list.get("book_name"));
            ps.setString(3, list.get("price"));
            ps.setString(4, list.get("offer"));
            ps.setString(5, img);
            int i = ps.executeUpdate();
            if (i > 0) {
                out.println("Data Uploaded Successfully <a href='list.jsp'>Show List</a>");

            } else {
                out.println("Data is not uploaded Correctly..!!");
            }
        } catch (Exception e) {
            System.out.println("Exception Occured : " + e);
        }

    }

}
