package ustc.sse.tims.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * @author ZHGQ
 * @project TIMSServer
 * @Package ustc.sse.tims.util
 * @date 2019/3/8-9:49
 * @Copyright: (c) 2019 USTC. All rights reserved.
 * @Description:
 */
public class FileFinder {

    public InputStream find(String filename) throws FileNotFoundException {
        File xml =new File(System.getProperty("java.io.tmpdir")+"/"+filename+".xml");
        return new FileInputStream(xml);
    }

}
