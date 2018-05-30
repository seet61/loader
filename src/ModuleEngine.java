import data.Module;
import loader.ModuleLoader;

import java.io.File;

public class ModuleEngine {
    public static void main(String args[]) {
        String modulePath = "c:\\Projects\\loader\\out\\production\\loader\\data";
        /**
         * Создаем загрузчик модулей.
         */
        ModuleLoader loader = new ModuleLoader(ClassLoader.getSystemClassLoader(), modulePath);

        /**
         * Получаем список доступных модулей.
         */
        File dir = new File(modulePath);
        String[] modules = dir.list();

        /**
         * Загружаем и исполняем каждый модуль.
         */
        for (String module: modules) {
            try {
                System.out.println(module);
                String moduleName = module.split(".class")[0];
                Class clazz = loader.loadClass(moduleName);
                /*
                Module execute = (Module) clazz.newInstance();

                execute.load();
                execute.run();
                execute.unload();
                */
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            /*
            catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            */
        }


    }
}
