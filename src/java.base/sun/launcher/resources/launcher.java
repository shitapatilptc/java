package sun.launcher.resources;

import java.util.ListResourceBundle;

public final class launcher extends ListResourceBundle {
    protected final Object[][] getContents() {
        return new Object[][] {
            { "java.launcher.X.macosx.usage", "\nThe following options are Mac OS X specific:\n    -XstartOnFirstThread\n                      run the main() method on the first (AppKit) thread\n    -Xdock:name=<application name>\n                      override default application name displayed in dock\n    -Xdock:icon=<path to icon file>\n                      override default icon displayed in dock\n\n" },
            { "java.launcher.X.usage", "\n    -Xbatch           disable background compilation\n    -Xbootclasspath/a:<directories and zip/jar files separated by {0}>\n                      append to end of bootstrap class path\n    -Xcheck:jni       perform additional checks for JNI functions\n    -Xcomp            forces compilation of methods on first invocation\n    -Xdebug           provided for backward compatibility\n    -Xdiag            show additional diagnostic messages\n    -Xdiag:resolver   show resolver diagnostic messages\n    -Xfuture          enable strictest checks, anticipating future default\n    -Xint             interpreted mode execution only\n    -Xinternalversion\n                      displays more detailed JVM version information than the\n                      -version option\n    -Xloggc:<file>    log GC status to a file with time stamps\n    -Xmixed           mixed mode execution (default)\n    -Xmn<size>        sets the initial and maximum size (in bytes) of the heap\n                      for the young generation (nursery)\n    -Xms<size>        set initial Java heap size\n    -Xmx<size>        set maximum Java heap size\n    -Xnoclassgc       disable class garbage collection\n    -Xprof            output cpu profiling data\n    -Xrs              reduce use of OS signals by Java/VM (see documentation)\n    -Xshare:auto      use shared class data if possible (default)\n    -Xshare:off       do not attempt to use shared class data\n    -Xshare:on        require using shared class data, otherwise fail.\n    -XshowSettings    show all settings and continue\n    -XshowSettings:all\n                      show all settings and continue\n    -XshowSettings:locale\n                      show all locale related settings and continue\n    -XshowSettings:properties\n                      show all property settings and continue\n    -XshowSettings:vm show all vm related settings and continue\n    -Xss<size>        set java thread stack size\n    -Xverify          sets the mode of the bytecode verifier\n    --add-reads <module>=<target-module>(,<target-module>)*\n                      updates <module> to read <target-module>, regardless\n                      of module declaration. \n                      <target-module> can be ALL-UNNAMED to read all unnamed\n                      modules.\n    --add-exports <module>/<package>=<target-module>(,<target-module>)*\n                      updates <module> to export <package> to <target-module>,\n                      regardless of module declaration.\n                      <target-module> can be ALL-UNNAMED to export to all\n                      unnamed modules.\n    --add-opens <module>/<package>=<target-module>(,<target-module>)*\n                      updates <module> to open <package> to\n                      <target-module>, regardless of module declaration.\n    --disable-@files  disable further argument file expansion\n    --patch-module <module>=<file>({0}<file>)*\n                      Override or augment a module with classes and resources\n                      in JAR files or directories.\n\nThese extra options are subject to change without notice.\n" },
            { "java.launcher.cls.error1", "Error: Could not find or load main class {0}\nCaused by: {1}: {2}" },
            { "java.launcher.cls.error2", "Error: Main method is not {0} in class {1}, please define the main method as:\n   public static void main(String[] args)" },
            { "java.launcher.cls.error3", "Error: Main method must return a value of type void in class {0}, please \ndefine the main method as:\n   public static void main(String[] args)" },
            { "java.launcher.cls.error4", "Error: Main method not found in class {0}, please define the main method as:\n   public static void main(String[] args)\nor a JavaFX application class must extend {1}" },
            { "java.launcher.cls.error5", "Error: JavaFX runtime components are missing, and are required to run this application" },
            { "java.launcher.cls.error6", "Error: LinkageError occurred while loading main class {0}\n\t{1}" },
            { "java.launcher.init.error", "initialization error" },
            { "java.launcher.jar.error1", "Error: An unexpected error occurred while trying to open file {0}" },
            { "java.launcher.jar.error2", "manifest not found in {0}" },
            { "java.launcher.jar.error3", "no main manifest attribute, in {0}" },
            { "java.launcher.javafx.error1", "Error: The JavaFX launchApplication method has the wrong signature, it\nmust be declared static and return a value of type void" },
            { "java.launcher.module.error1", "module {0} does not have a MainClass attribute, use -m <module>/<main-class>" },
            { "java.launcher.module.error2", "Error: Could not find or load main class {0} in module {1}" },
            { "java.launcher.module.error3", "Error: Unable to load main class {0} from module {1}\n\t{2}" },
            { "java.launcher.opt.datamodel", "    -d{0}\t  Deprecated, will be removed in a future release\n" },
            { "java.launcher.opt.footer", "    -cp <class search path of directories and zip/jar files>\n    -classpath <class search path of directories and zip/jar files>\n    --class-path <class search path of directories and zip/jar files>\n                  A {0} separated list of directories, JAR archives,\n                  and ZIP archives to search for class files.\n    -p <module path>\n    --module-path <module path>...\n                  A {0} separated list of directories, each directory\n                  is a directory of modules.\n    --upgrade-module-path <module path>...\n                  A {0} separated list of directories, each directory\n                  is a directory of modules that replace upgradeable\n                  modules in the runtime image\n    -m <module>[/<mainclass>]\n    --module <modulename>[/<mainclass>]\n                  the initial module to resolve, and the name of the main class\n                  to execute if not specified by the module\n    --add-modules <modulename>[,<modulename>...]\n                  root modules to resolve in addition to the initial module.\n                  <modulename> can also be ALL-DEFAULT, ALL-SYSTEM,\n                  ALL-MODULE-PATH.\n    --limit-modules <modulename>[,<modulename>...]\n                  limit the universe of observable modules\n    --list-modules [<modulename>[,<modulename>...]]\n                  list the observable modules and exit\n    --dry-run     create VM but do not execute main method.\n                  This --dry-run option may be useful for validating the\n                  command-line options such as the module system configuration.\n    -D<name>=<value>\n                  set a system property\n    -verbose:[class|gc|jni]\n                  enable verbose output\n    -version      print product version to the error stream and exit\n    --version     print product version to the output stream and exit\n    -showversion  print product version to the error stream and continue\n    --show-version\n                  print product version to the output stream and continue\n    -? -h -help\n                  print this help message to the error stream\n    --help        print this help message to the output stream\n    -X            print help on extra options to the error stream\n    --help-extra  print help on extra options to the output stream\n    -ea[:<packagename>...|:<classname>]\n    -enableassertions[:<packagename>...|:<classname>]\n                  enable assertions with specified granularity\n    -da[:<packagename>...|:<classname>]\n    -disableassertions[:<packagename>...|:<classname>]\n                  disable assertions with specified granularity\n    -esa | -enablesystemassertions\n                  enable system assertions\n    -dsa | -disablesystemassertions\n                  disable system assertions\n    -agentlib:<libname>[=<options>]\n                  load native agent library <libname>, e.g. -agentlib:jdwp\n                  see also -agentlib:jdwp=help\n    -agentpath:<pathname>[=<options>]\n                  load native agent library by full pathname\n    -javaagent:<jarpath>[=<options>]\n                  load Java programming language agent, see java.lang.instrument\n    -splash:<imagepath>\n                  show splash screen with specified image\n                  HiDPI scaled images are automatically supported and used\n                  if available. The unscaled image filename, e.g. image.ext,\n                  should always be passed as the argument to the -splash option.\n                  The most appropriate scaled image provided will be picked up\n                  automatically.\n                  See the SplashScreen API documentation for more information.\n    @<filepath>   read options from the specified file\n\nTo specify an argument for a long option, you can use --<name>=<value> or\n--<name> <value>.\n" },
            { "java.launcher.opt.header", "Usage: {0} [options] class [args...]\n           (to execute a class)\n   or  {0} [options] -jar jarfile [args...]\n           (to execute a jar file)\n   or  {0} [options] -p <modulepath> -m <modulename>[/<mainclass>] [args...]\n           (to execute the main class in a module)\n\nwhere options include:\n\n" },
            { "java.launcher.opt.hotspot", "    {0}\t  is a synonym for the \"{1}\" VM  [deprecated]\n" },
            { "java.launcher.opt.vmselect", "    {0}\t  to select the \"{1}\" VM\n" },
        };
    }
}
