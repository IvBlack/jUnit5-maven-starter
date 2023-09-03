package com.ivdev.junit;

import org.junit.platform.engine.DiscoverySelector;
import org.junit.platform.engine.discovery.DiscoverySelectors;
import org.junit.platform.engine.support.hierarchical.ThrowableCollector;
import org.junit.platform.launcher.LauncherDiscoveryListener;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;

import java.io.PrintWriter;

/*
создается launcher, запускающий тесты
request билдится по цепочке, отдается в execute()
selectors ищут наши тесты по: дирректории, пакету, классу либо пути - как это делает intellij
*/
public class TestLauncher {
    public static void main(String[] args) {
        var launcher = LauncherFactory.create();

        //Простой TestExecutionListener, который генерирует сводку выполнения теста в консоль, стр. 35
        var summaryGeneratingListeners = new SummaryGeneratingListener();

        LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder
                .request()
//                .selectors(DiscoverySelectors.selectClass(UserServiceTest.class))
                .selectors(DiscoverySelectors.selectPackage("com.ivdev.junit.service"))
//                .listeners()
                .build();
        launcher.execute(request, summaryGeneratingListeners);

        try (var writer = new PrintWriter(System.out)) {
            summaryGeneratingListeners.getSummary().printTo(writer);
        }
    }
}
