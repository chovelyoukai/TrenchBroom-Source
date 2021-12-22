# TrenchBroom Source Tools

---

## What is this?

This is a collection of tools, config files, and textures for creating Source engine maps with TrenchBroom. This is geared mainly to mapping on Linux. It includes the following:

- MapToVmf, a tool for converting Quake .map files to Source VMFs
- A ready-made Source engine game profile and FGD formatted for TrenchBroom
- Special scripts for running native and Wine versions of VBSP, VRAD, and VVIS
- A handful of basic tool and dev texture placeholders for working with TrenchBroom

## How to use it

First, follow these steps:

- Make sure you have Java installed.
- Put the contents of the `scripts` folder in your `bin` or `bin/linux64` folder alongside your compilation tools.
- Put the `textures` folder in your game directory. For example, for Team Fortress 2 it's the `tf` folder, for Momentum Mod it's the `momentum` folder, for Counter Strike: Source, it's the `cstrike` folder, and so on. You should have something like `tf/textures` when you're done.

- Put the `Source` folder in your TrenchBroom game profiles folder, probably in `~/.TrenchBroom/games`.

When you open TrenchBroom next, you should be able to select "Source" as an option. Now, all you need to do is set the correct game directory. This can be done by going to View > Preferences > Source > Game Path, and then entering the correct path for your game.

Finally, when you make your maps, save them in `(game directory)/mapsrc`. For example, for Momentum Mod you would save your maps in `momentum/mapsrc`. If you want to use a different directory, you will need to change your compilation profile to use that folder instead.

## MapToVmf

MapToVmf is the heart of these tools. It has some special features to allow the creation of Source maps with Quake mapping tools. It's only been tested with TrenchBroom but it probably works with other editors too.

### Options

`--lightmapscale scale` or `-l scale`

> Specifies the global lightmap scale for every brush face. The default lightmap scale is 128.

`--textureprefix prefix` or `-t prefix`

> Sets a prefix to be used on every texture. For example, a texture of `GRASS01` and a prefix of `WORLD/` would produce an ouput of `WORLD/GRASS01`.

`--standard` or `-s`

> Convert from "standard" axial coordinates to Valve style coordinates. This is only partially working, and it is not recommended to use.

### Entity I/O

MapToVmf can handle specially formatted key-value pairs to output entity I/O connections to the VMF. The basic format is this:

> Key: `*name`
>
> Value: `Output,target,Input,Parameter,Delay,Repeat`

For example, an input to change the color of an entity called `bob` when the owning entity is triggered, with a delay of 4.55 and a repeat of 9 could look like this:

> Key: `*io3`
>
> Value: `OnTrigger,bob,Color,255 255 0,4.55,9`

The contents of the key are not important, as long as they start with an asterisk and are unique. Only the contents of the value will actually be used in the VMF.

### Other Features

MapToVmf automatically deletes any patches it finds in the map. This might be useful for porting Quake III maps.