package io.armory.plugin.preconfigured

import com.netflix.spinnaker.kork.plugins.api.ExtensionConfiguration

@ExtensionConfiguration("armory.preconfiguredJobStage")
data class PluginConfig(var account: String?, var namespace: String?, var application: String?)