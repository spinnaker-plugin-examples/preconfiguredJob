package io.armory.plugin.preconfigured

import com.netflix.spinnaker.kork.plugins.api.PluginSdks
import com.netflix.spinnaker.orca.api.preconfigured.jobs.PreconfiguredJobConfigurationProvider
import com.netflix.spinnaker.orca.clouddriver.config.KubernetesPreconfiguredJobProperties
import org.pf4j.Extension
import org.pf4j.Plugin
import org.pf4j.PluginWrapper


class PreconfiguredPlugin(wrapper: PluginWrapper): Plugin(wrapper) {

    override fun start() {
        System.out.println("PreconfiguredPlugin.start()")
    }

    override fun stop() {
        System.out.println("PreconfiguredPlugin.stop()")
    }
}

@Extension
class PreconfiguredStage(val pluginSdks: PluginSdks, val configuration: PluginConfig) : PreconfiguredJobConfigurationProvider {
    override fun getJobConfigurations(): List<KubernetesPreconfiguredJobProperties> {
        val jobProperties = pluginSdks.yamlResourceLoader().loadResource("io/armory/plugin/preconfigured/job.yaml", KubernetesPreconfiguredJobProperties::class.java)
        if (!configuration.account.isNullOrEmpty()) {
            jobProperties.account = configuration.account
        }
        if (!configuration.credentials.isNullOrEmpty()) {
            jobProperties.credentials = configuration.credentials
        }
        if (!configuration.namespace.isNullOrEmpty()) {
            jobProperties.manifest.metadata.namespace = configuration.namespace
        }
        if (!configuration.application.isNullOrEmpty()) {
            jobProperties.application = configuration.application
        }
        return arrayListOf(jobProperties)
    }
}
