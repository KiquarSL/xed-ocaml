# OCaml Support

Xed-Editor extension providing syntax highlighting, language configuration, and runners for OCaml and the Dune build system.

## Installation

Install through the Xed-Editor extension marketplace, or download the latest release ZIP and install via Settings > Extensions > Install from storage.

## OCaml Toolchain Setup

To use the runners, install the OCaml toolchain:

```bash
bash -c "sh <(curl https://opam.ocaml.org/install.sh)"

# Initialize opam
opam init

# Install OCaml
opam switch create 5.1.0
eval $(opam env)

# Install dune
opam install dune
```

Or using your system package manager:
WARNING: it can have old versions!

```bash
# Debian / Ubuntu
apt install ocaml dune opam
```

## Building

Debug build:
```bash
./gradlew assembleDebug
./gradlew :app:createFinalZip
```

Release build:
```bash
./gradlew assembleRelease
./gradlew :app:createFinalZip
```
