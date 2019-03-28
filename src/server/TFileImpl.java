package server;

        import corba.MyDataHolder;
        import corba.MyListHolder;
        import corba.TFilePOA;

        import java.io.File;
        import java.io.FileInputStream;
        import java.io.FileOutputStream;
        import java.util.ArrayList;
        import java.util.List;


public class TFileImpl extends TFilePOA {


    // 返回服务器上所有可下载的文件名
    @Override
    public void listAllFile(MyListHolder allFileNames) {

        List<String> fileNameList = new ArrayList<>();
        File dir = new File("serverFile/");
        File[] files = dir.listFiles();
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                // 是文件（跳过目录文件）
                if (files[i].isFile()) {
                    fileNameList.add(files[i].getName());
                }
            }
        }
        // 转为String数组
        String[] allNames = new String[fileNameList.size()];
        fileNameList.toArray(allNames);
        allFileNames.value = allNames;

    }

    @Override
    public boolean download(String fileName, MyDataHolder fileData) {
        try {
            FileInputStream fis = new FileInputStream(fileName);
            int n = fis.available();          // 流字节长度
            byte[] data = new byte[n];
            fis.read(data);                 // 将字节读到字节流数组
            fis.close();
            fileData.value = data;
            return true;
        } catch (Exception e) {
            fileData.value=new byte[0];
            return false;
        }
    }


    @Override
    public boolean upload(String fileName, byte[] fileData) {
        try {
            FileOutputStream fos = new FileOutputStream("serverFile/" + fileName);
            fos.write(fileData);
            fos.flush();
            fos.close();
            return true;
        } catch (Exception e) {
            return false;
        }

    }

}
