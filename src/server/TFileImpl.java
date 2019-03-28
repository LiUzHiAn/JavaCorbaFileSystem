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


    // ���ط����������п����ص��ļ���
    @Override
    public void listAllFile(MyListHolder allFileNames) {

        List<String> fileNameList = new ArrayList<>();
        File dir = new File("serverFile/");
        File[] files = dir.listFiles();
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                // ���ļ�������Ŀ¼�ļ���
                if (files[i].isFile()) {
                    fileNameList.add(files[i].getName());
                }
            }
        }
        // תΪString����
        String[] allNames = new String[fileNameList.size()];
        fileNameList.toArray(allNames);
        allFileNames.value = allNames;

    }

    @Override
    public boolean download(String fileName, MyDataHolder fileData) {
        try {
            FileInputStream fis = new FileInputStream(fileName);
            int n = fis.available();          // ���ֽڳ���
            byte[] data = new byte[n];
            fis.read(data);                 // ���ֽڶ����ֽ�������
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
