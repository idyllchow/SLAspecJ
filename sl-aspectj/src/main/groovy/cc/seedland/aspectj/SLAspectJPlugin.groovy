package cc.seedland.aspectj

import com.android.build.gradle.AppExtension
import com.android.build.gradle.AppPlugin
import org.gradle.api.Plugin
import org.gradle.api.Project

class SLAspectJPlugin implements  Plugin<Project> {

    @Override
    void apply(Project project) {

        project.task('testMyTask') << {
            println "Hello gradle plugin"
        }

        project.repositories {
            mavenLocal()
        }

        project.dependencies {
            compile 'org.aspectj:aspectjrt:1.8.9'
        }

        project.extensions.create("aspectjx", AspectjExtension)

        if (project.plugins.hasPlugin(AppPlugin)) {
            //build time trace
//            project.gradle.addListener(new TimeTrace())

            //register AspectTransform
            AppExtension android = project.extensions.getByType(AppExtension)
            android.registerTransform(new cc.seedland.aspectj.AspectTransform(project))
        }


    }
}