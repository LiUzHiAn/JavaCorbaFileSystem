package server;



import corba.TFile;
import corba.TFileHelper;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

import java.util.Properties;

//-ORBInitialPort 1050 -ORBInitialHost localhost
public class FileServer {
    public static void main(String[] args) {
        try {
            Properties properties = new Properties();
            properties.put("org.omg.CORBA.ORBInitialHost", "127.0.0.1");  //ָ��ORB��ip��ַ
            properties.put("org.omg.CORBA.ORBInitialPort", "1050");       //ָ��ORB�Ķ˿�

            // ��ʼ��ORB����
            ORB orb = ORB.init(args, properties);

            // ��ȡ��rootPOA�����ã�����POAManager
            POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootpoa.the_POAManager().activate();

            // ����һ���ӿ�ʵ�ֶ���
            TFileImpl fileServant = new TFileImpl();

            // ������ʵ�ֶ��󽻸�POA���������Ⱪ¶�ӿ�
            org.omg.CORBA.Object ref = rootpoa.servant_to_reference(fileServant);
            TFile href = TFileHelper.narrow(ref);

            // Naming ������
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            // ��Զ�̶���Naming
            String name = "FileOps";
            NameComponent[] path = ncRef.to_name(name);
            ncRef.bind(path, href);

            System.out.println("FileServer ready and waiting...");

            // �ȴ��ͻ��˵���
            orb.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("FileServer Exiting...");
    }
}

