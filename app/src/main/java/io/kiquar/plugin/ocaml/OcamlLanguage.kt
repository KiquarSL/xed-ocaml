package io.kiquar.plugin.ocaml

import android.content.res.Resources
import com.rk.file.FileType
import com.rk.icons.Icon

class OcamlLanguage(resources: Resources) : FileType {
    override val extensions = listOf("ml", "mli", "mly", "mll")
    override val textmateScope = "source.ocaml"
    override val name = "ocaml"
    override val title = "OCaml"
    override val icon = Icon.ExternalResourceIcon(R.drawable.ocaml, resources)
}
