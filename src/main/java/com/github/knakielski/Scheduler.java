package com.github.knakielski;

import com.github.knakielski.cache.CacheManager;
import org.jobrunr.configuration.JobRunr;
import org.jobrunr.scheduling.BackgroundJob;
import org.jobrunr.storage.InMemoryStorageProvider;

public class Scheduler {

    private static final CacheManager cacheManager = CacheManager.getInstance();

    static {
        JobRunr.configure()
               .useStorageProvider(new InMemoryStorageProvider())
               .useBackgroundJobServer(64)
               .useDashboard(8081)
               .initialize();
    }

    public static synchronized void attach() {
        BackgroundJob.scheduleRecurrently("0 0 * * *", cacheManager::invalidCache);
    }
}
