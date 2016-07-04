package org.landasource.rempi.manager;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.concurrent.Executors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableAutoConfiguration
@SpringBootApplication
@EnableTransactionManagement
public class RempiManagerApplication {

    public static void main(final String[] args) {
        Executors.newCachedThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                watch();
            }
        });
        SpringApplication.run(RempiManagerApplication.class, args);
    }

    private static void watch() {

        final Path path = Paths.get(System.getProperty("user.dir"));
        // We obtain the file system of the Path
        final FileSystem fs = path.getFileSystem();

        // We create the new WatchService using the new try() block
        try (WatchService service = fs.newWatchService()) {

            path.register(service, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);

            System.out.println("Watch Service registered for dir: " + path.getFileName());

            while (true) {
                WatchKey key;
                try {
                    key = service.take();
                } catch (final InterruptedException ex) {
                    return;
                }

                for (final WatchEvent<?> event : key.pollEvents()) {
                    final WatchEvent.Kind<?> kind = event.kind();

                    @SuppressWarnings("unchecked")
                    final
                    WatchEvent<Path> ev = (WatchEvent<Path>) event;
                    final Path fileName = ev.context();

                    System.out.println(kind.name() + ": " + fileName);

                    if (kind == ENTRY_MODIFY &&
                            fileName.toString().equals("DirectoryWatchDemo.java")) {
                        System.out.println("My source file has changed!!!");
                    }
                }

                final boolean valid = key.reset();
                if (!valid) {
                    break;
                }
            }

        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }



}
