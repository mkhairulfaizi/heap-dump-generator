package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.management.MBeanServer;
import java.io.File;
import java.lang.management.ManagementFactory;
import com.sun.management.HotSpotDiagnosticMXBean;

@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);

        // Generate a heap dump at startup
        generateHeapDump("heapdump-startup.hprof");
    }

    public static void generateHeapDump(String fileName) {
        try {
            File heapDumpDir = new File("./heapdumps");
            if (!heapDumpDir.exists()) {
                heapDumpDir.mkdirs();
            }

            MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
            HotSpotDiagnosticMXBean diagnosticMXBean = ManagementFactory.newPlatformMXBeanProxy(
                mBeanServer, "com.sun.management:type=HotSpotDiagnostic", HotSpotDiagnosticMXBean.class);

            File heapDumpFile = new File(heapDumpDir, fileName);
            diagnosticMXBean.dumpHeap(heapDumpFile.getAbsolutePath(), true);
            System.out.println("Heap dump generated: " + heapDumpFile.getAbsolutePath());
        } catch (Exception e) {
            System.err.println("Error generating heap dump: " + e.getMessage());
            e.printStackTrace();
        }
    }

}

@RestController
class HelloController {
    @GetMapping("/hello")
    public String sayHello() {
        return "Hello, Kubernetes!";
    }
}
