package io.kiquar.plugin.ocaml

import android.content.res.Resources
import com.rk.file.FileType
import com.rk.file.BuiltinFileType

class DuneLanguage(resources: Resources) : FileType {
    override val extensions = listOf("dune", "dune-project")
    override val textmateScope = "source.dune"
    override val name = "dune"
    override val title = "Dune"
	override val names = listof("dune", "dune-project")
    override val icon = BuiltinFileType.PROPERTIES.icon
}
