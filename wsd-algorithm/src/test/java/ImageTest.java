import com.freewayso.image.combiner.ImageCombiner;
import com.freewayso.image.combiner.enums.OutputFormat;

import java.awt.*;
import java.io.InputStream;

import static com.freewayso.image.combiner.enums.ZoomMode.WidthHeight;

/**
 * <h3>wsd-project</h3>
 * <p></p>
 *
 * @author : 王松迪
 * 2024-05-07 13:38
 **/
public class ImageTest {

    public static void main(String[] args) throws Exception {


        //合成器（指定背景图和输出格式，整个图片的宽高和相关计算依赖于背景图，所以背景图的大小是个基准）
        ImageCombiner combiner = new ImageCombiner(700, 2500, Color.YELLOW, OutputFormat.JPG);

        int x = 0;
        int y = 0;
        int iconWidth = 40;
        int iconHeight = 40;
        int nameFontSize = 30;


        int name_icon_interval = 70;
        int ugc_interval = 50;
        int line_interval = 150;
        int ugc_image_width = 400;
        int ugc_image_height = 300;

        for (int i = 0; i < 10; i++) {
            //头像
            combiner.addImageElement("https://qiniustatic.wodidashi.com/h5/front-backstage/dewwe_1562037342632.jpg", x, y, iconWidth, iconHeight, WidthHeight);
            //昵称
            combiner.addTextElement("（被举报人）用户昵称", nameFontSize, x + name_icon_interval, y);
            //ugc
            combiner.addImageElement("https://qiniustatic.wodidashi.com/h5/front-backstage/dewwe_1562037342632.jpg", x + ugc_interval, y + ugc_interval, ugc_image_width, ugc_image_height, WidthHeight);

            y += (line_interval + ugc_image_height);
        }


        //加文本元素
//        combiner.addTextElement("周末大放送1", 30, 0, 500);
//
//        combiner.addImageElement("https://qiniustatic.wodidashi.com/h5/front-backstage/dewwe_1562037342632.jpg", 0, 700, 500, 200, WidthHeight);
//
//        combiner.addTextElement("周末大放送1", 30, 0, 1100);
//
//        combiner.addImageElement("https://qiniustatic.wodidashi.com/h5/front-backstage/dewwe_1562037342632.jpg", 0, 1300, 500, 200, WidthHeight);
//
//        combiner.addTextElement("周末大放送2", 30, 0, 1700);
//
//        combiner.addImageElement("https://qiniustatic.wodidashi.com/h5/front-backstage/dewwe_1562037342632.jpg", 0, 1900, 500, 200, WidthHeight);
//
//        combiner.addTextElement("周末大放送3", 30, 0, 2100);
//
//        combiner.addImageElement("https://qiniustatic.wodidashi.com/h5/front-backstage/dewwe_1562037342632.jpg", 0, 2300, 500, 200, WidthHeight);

        //执行图片合并
        combiner.combine();

        //可以获取流（并上传oss等）
        InputStream is = combiner.getCombinedImageStream();

        String path = ImageTest.class.getResource("/").getPath();
        //也可以保存到本地
        combiner.save(path + "/test1.jpg");

    }
}
