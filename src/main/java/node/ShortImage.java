package node;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;

import javax.imageio.ImageIO;

import sun.awt.image.PNGImageDecoder;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.sun.imageio.plugins.png.PNGImageWriter;

public class ShortImage {

    public final static  String DDIST="D:\\work\\image\\short\\";
    public final static  String DSRC="D:\\work\\image\\";

    public static void main(String[] args) {
        createThumbnail(DSRC+"1.jpg", DDIST+"a.jpg", 100, 100);
        createThumbnail(DSRC+"2.jpg", DDIST+"b.jpg", 100, 100);
        createThumbnail(DSRC+"3.png", DDIST+"c.png", 100, 100);
    }

    public static void createThumbnail(String src,String dist,float width,float height){
        try {
            File srcFile = new File(src);
            if(!srcFile.exists()){
                System.out.println("文件不存在");
                return;
            }
            BufferedImage image = ImageIO.read(srcFile);
            //获得缩放比例
            double ratio = 1.0;
            //判断如果高、宽都不大于设定值，则不处理
            if(image.getHeight() > height || image.getWidth() > width){
                if( image.getHeight() > image.getWidth()){
                    ratio = height / image.getHeight();
                }else{
                    ratio = width / image.getWidth();
                }
            }
            //计算新的图面宽度和高度
            int newWidth = (int)(image.getWidth() * ratio);
            int newHeight = (int)(image.getHeight() * ratio);
            BufferedImage bfiImage = new BufferedImage(newWidth, newHeight,BufferedImage.TYPE_INT_BGR);
            bfiImage.getGraphics().drawImage(
                    image.getScaledInstance(newWidth, newHeight,Image.SCALE_SMOOTH),0,0,null);
            FileOutputStream os = new FileOutputStream(dist);
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(os);
            encoder.encode(bfiImage);
            os.close();
            System.out.println("创建缩略图成功");
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("创建缩略图发生异常"+e.getMessage());
        }
    }
}
