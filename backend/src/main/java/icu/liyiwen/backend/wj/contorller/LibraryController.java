package icu.liyiwen.backend.wj.contorller;

import icu.liyiwen.backend.result.Result;
import icu.liyiwen.backend.wj.bean.Book;
import icu.liyiwen.backend.wj.service.BookService;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @author nanfang
 * @create 2020/02/07/10:52
 */
@RestController
public class LibraryController {
    @Resource(name="bookService")
    private BookService bookService;

    //返回所有的图书
    @CrossOrigin
    @GetMapping("api/books")
    public List<Book>findAll() throws Exception{
        return bookService.findAll();
    }

    //根据用户账号返回所有的书
    @CrossOrigin
    @PostMapping("api/booksByUid")
    public List<Book>findAllByUid(@RequestBody Book book){
        return bookService.findAllByUid(book.getUid());
    }

    //根据种类查询图书
    @CrossOrigin
    @GetMapping("api/categories/{cid}/books")
    public List<Book> listByCategory(@PathVariable("cid") String cid) throws Exception {
        if (!cid.equals("0")) {
            return bookService.findAllByCategory(cid);
        } else {
            return findAll();
        }
    }
    //根据种类和用户名查询用户添加的图书
    @CrossOrigin
    @PostMapping("api/categories/uid/UserBooks")
    public List<Book> listByCategoryAndUid(@RequestBody Book book){
        System.out.println(book);
        if (!book.getCid().equals("0")) {
            return bookService.findAllByCategoryAndUid(book.getCid(),book.getUid());
        } else {
            return bookService.findAllByUid(book.getUid());
        }
    }


    //根据id删除图书
    @CrossOrigin
    @PostMapping("api/delete")
    public Result deleteBookById(@RequestBody Book book){
        int num=bookService.deleteBookById(book.getId());
        if(num==0){
            return new Result(400);
        } else {
            return new Result(200);
        }
    }

    //根据id和用户编号删除图书，并删除本地服务器存储的图片
    @PostMapping("api/deleteById")
    @CrossOrigin
    public Result deleteBookByIdAndUid(@RequestBody Book book){
        int num=bookService.deleteBookByIdAndUid(book);
        if(num==0){
            return new Result(400);
        } else {
            if(!book.getCover().equals("")){
                String cover=book.getCover().split("/api/file/")[1];
                deleteImg(cover);
            }
            if(!book.getImg_1().equals("")){
                String img_1=book.getImg_1().split("/api/file/")[1];
                deleteImg(img_1);
            }
            if(!book.getImg_2().equals("")){
                String img_2=book.getImg_2().split("/api/file/")[1];
                deleteImg(img_2);
            }
            if(!book.getImg_3().equals("")){
                String img_3=book.getImg_3().split("/api/file/")[1];
                deleteImg(img_3);
            }
            if(!book.getImg_4().equals("")){
                String img_4=book.getImg_4().split("/api/file/")[1];
                deleteImg(img_4);
            }
            if(!book.getImg_5().equals("")){
                String img_5=book.getImg_5().split("/api/file/")[1];
                deleteImg(img_5);
            }
            return new Result(200);
        }
    }
    @CrossOrigin
    public void deleteImg(String imgName){
        String imgUrl="D:\\SpringBoot+Vue图书商城\\SpringBoot+Vue图书商城\\backend\\src\\main\\resources\\static\\img\\"+imgName;
        File img=new File(imgUrl);
        img.delete();
    }
    //添加一本图书
    @CrossOrigin
    @PostMapping("api/insert")
    public Result insertBook(@RequestBody Book book){
        System.out.println(book);
        if(bookService.insertBook(book)==0){
            return new Result(400);
        } else {
            return new Result(200);
        }
    }
    //根据书名查找图书
    //使用form表单传递数据
    @CrossOrigin
    @PostMapping("api/search")
    public List<Book> searchBooksByTitle(@RequestParam Map<String,Object> keywords, HttpSession session){
        return bookService.finAllByTitle((String)keywords.get("keywords"));
    }

    //上传图片
    @CrossOrigin
    @PostMapping("api/uploadImg")
    public String coversUpload(MultipartFile file) throws Exception {
        //查看图片类型
        String type=file.getContentType().split("/")[1];
        if(type==null||file.getSize()>1048576){
            return null;
        }
        String folder = "D:\\SpringBoot+Vue图书商城\\SpringBoot+Vue图书商城\\backend\\src\\main\\resources\\static\\img";
        File imageFolder = new File(folder);
        File f = new File(imageFolder, getRandomString(10) + file.getOriginalFilename()
                .substring(file.getOriginalFilename().length() - 4));
        if (!f.getParentFile().exists())
            //如果不存在文件则创建文件夹
            f.getParentFile().mkdirs();
        try {
            //存入图片
            file.transferTo(f);
            //对存入的图片进行压缩,并转换为jpeg格式
            Thumbnails.of(f)
                    .outputFormat("jpg")
                    .scale(0.5f)//图片比例压缩
                    .outputQuality(0.5f)//图片清晰度压缩
                    .toFile(f.getPath().split("\\.")[0]+".jpg");
            if(!type.equals("jpeg")){
                //删除图片
                f.delete();
            }
            String imgURL = "http://localhost:8443/api/file/" + f.getName().split("\\.")[0]+".jpg";
            return imgURL;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
    //删除图片
    @CrossOrigin
    @PostMapping("api/deleteImg")
    public void deleteImg(@RequestParam Map<String,Object> requestMap){
        String imgName=requestMap.get("imgUrl").toString().split("/api/file/")[1];
        deleteImg(imgName);
    }

    //生成length个随机数组成的字符串
    public String getRandomString(int length) {
        String base = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    @GetMapping("/api/file/{fileName}")
    public void readImage(@PathVariable("fileName") String fileName, HttpServletRequest request, HttpServletResponse response) {
        //取路径
        String path = "D:\\SpringBoot+Vue图书商城\\SpringBoot+Vue图书商城\\backend\\src\\main\\resources\\static\\img\\" + fileName;
        FileInputStream fis = null;
        OutputStream os = null;
        try {
            fis = new FileInputStream(path);
            os = response.getOutputStream();
            int count = 0;
            byte[] buffer = new byte[1024 * 8];
            while ((count = fis.read(buffer)) != -1) {
                os.write(buffer, 0, count);
                os.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            fis.close();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
