package io.kiquar.plugin.ocaml

import android.app.Activity
import android.os.Bundle
import androidx.annotation.Keep
import com.rk.extension.ExtensionAPI
import com.rk.extension.ExtensionContext
import com.rk.file.FileTypeManager
import com.rk.runner.RunnerManager
import io.github.rosemoe.sora.langs.textmate.registry.FileProviderRegistry
import io.github.rosemoe.sora.langs.textmate.registry.GrammarRegistry
import io.github.rosemoe.sora.langs.textmate.registry.provider.AssetsFileResolver
import io.kiquar.plugin.ocaml.runner.OcamlRunner
import io.kiquar.plugin.ocaml.runner.DuneRunner

@Keep
@Suppress("unused")
class Main(context: ExtensionContext) : ExtensionAPI(context) {
    private var fileResolver: AssetsFileResolver? = null
    private var ocamlLanguage: OcamlLanguage? = null
    private var duneLanguage: DuneLanguage? = null
    private var opamLanguage: OpamLanguage? = null
    private var ocamlRunner: OcamlRunner? = null
    private var duneRunner: DuneRunner? = null

    override fun onExtensionLoaded() {
        val fileProviderRegistry = FileProviderRegistry.getInstance()
        fileResolver = AssetsFileResolver(context.assets)
        fileProviderRegistry.addFileProvider(fileResolver)

        val grammarRegistry = GrammarRegistry.getInstance()
        grammarRegistry.loadGrammars("lang/language.json")

        OcamlLanguage(context.resources).also {
            ocamlLanguage = it
            FileTypeManager.register(it)
        }

        DuneLanguage(context.resources).also {
            duneLanguage = it
            FileTypeManager.register(it)
        }

        OpamLanguage(context.resources).also {
            opamLanguage = it
            FileTypeManager.register(it)
        }

        OcamlRunner().also {
            ocamlRunner = it
            RunnerManager.registerRunner(it)
        }

        DuneRunner().also {
            duneRunner = it
            RunnerManager.registerRunner(it)
        }
    }

    override fun onInstalled() {
    }
    
    override fun onLoad() {}

    override fun onUninstalled() {
        dispose()
    }

    private fun dispose() {
        val fileProviderRegistry = FileProviderRegistry.getInstance()
        fileResolver?.let {
            fileProviderRegistry.removeFileProvider(it)
        }
        ocamlRunner?.let {
            RunnerManager.unregisterRunner(it)
        }
        duneRunner?.let {
            RunnerManager.unregisterRunner(it)
        }
    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {}
    override fun onActivityDestroyed(activity: Activity) {}
    override fun onActivityPaused(activity: Activity) {}
    override fun onActivityResumed(activity: Activity) {}
    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}
    override fun onActivityStarted(activity: Activity) {}
    override fun onActivityStopped(activity: Activity) {}
}
