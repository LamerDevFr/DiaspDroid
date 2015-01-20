package fr.android.scaron.diaspdroid;

import android.app.Application;

import org.acra.ACRA;
import org.acra.annotation.ReportsCrashes;


@ReportsCrashes(
        formKey = "", // This is required for backward compatibility but not used
        formUri = "https://collector.tracepot.com/301ca578",
        applicationLogFile = "/sdcard/Android/data/fr.scaron.diaspdroid/logs/diaspdroid.log",
        //customReportContent = { ReportField.APP_VERSION_CODE, ReportField.APP_VERSION_NAME, ReportField.ANDROID_VERSION, ReportField.PHONE_MODEL, ReportField.CUSTOM_DATA, ReportField.STACK_TRACE, ReportField.LOGCAT },
        logcatArguments = { "-t", "100", "-v", "long", "ActivityManager:I", "DiaspDroid:D", "*:S" }
        //,
        //logcatArguments = { "-t", "100", "-v", "long", "ActivityManager:I", "DiaspDroid:D", "*:S" }
)
public class DiaspDroid extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // The following line triggers the initialization of ACRA
        ACRA.init(this);
        ACRA.getErrorReporter().checkReportsOnApplicationStart();
    }
}
