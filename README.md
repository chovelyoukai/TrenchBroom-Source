## Things You Will Need:
1. Steam
2. Wine
3. Java
4. Momentum Mod
5. Trenchbroom

## Setting Up the Environment:
1. Open Steam.
2. Enter `steam://open/console` in a web browser.
3. Type `download_depot 243750 243752` into the Steam console to download the Source SDK 2013 for Windows (you need the compile tools from it).
4. Copy the files from `~/.steam/steam/ubuntu12_32/steamapps/content/app_243750/depot_243752/bin` into the `bin` directory of your game (this is the top level bin directory, not the one in the `momentum` folder). The `bin` directory should now have a couple of subfolders, a ton of `.so` files that were already there, and a bunch of `.dll` and `.exe` folders that you copied into it.

5. Unzip the contents of this readme into your game folder (the one that has the `momentum` folder in it).
6. Open Trenchbroom and create a new map with the "Generic" game type.

### IMPORTANT NOTE
Always select ***Valve*** as the map type when creating a new map. Otherwise you could waste a lot of time blocking things out before you realize it doesn't compile properly.

7. In Trenchbroom, go to view > preferences and select the Generic game.
8. Set the Game Path to your `momentum` folder.
9. Now, use Trenchbroom as normal. You can use the included `tb_momentum.fgd` for your entities, included in the `momentum` folder.

## Compiling Manually

You can use the included map2vmf.sh script in the bin folder to convert .map files to .vmf. The arguments are as follows:
`map2vmf.sh OUTPUT_FILE [-lightmapscale n] INPUT_FILE`

The `-lightmapscale` option sets the lightmap scale for the entire map. It defaults to 128.

Then, you can use the included vbsp.sh, vvis.sh, and vrad.sh files to compile and copy the resulting bsp to wherever you want it to go. For example:

```
cp ../momentum/mapsrc/test.map ./test.map
map2vmf.sh test.vmf -lightmapscale 16 test.map
vbsp.sh test.vmf
vvis.sh test.bsp
vrad.sh -final -StaticPropPolys -StaticPropLighting test.bsp
cp test.bsp ../momentum/maps/test.map
```

## Setting Up Compilation In Trenchbroom

Instead of doing all the copying and compiling manually, or writing a script for it, you can set it up in Trenchbroom for you.

1. In Trenchbroom, go to Run > Compile Map...
2. Click the leftmost + button to create a new profile.
3. Set your working directory to be the bin directory with the compile tools in it with this parameter:

`${GAME_DIR_PATH}/../bin/`

4. Assuming you are saving your maps in momentum/mapsrc, add a Copy File command with the middle + button and add these parameters:

    Source: `${GAME_DIR_PATH}/mapsrc/${MAP_FULL_NAME}`

    Target: `${GAME_DIR_PATH}/../bin/`

5. Add a Run Tool command for the map2vmf conversion:

    Tool: `${GAME_DIR_PATH}/../bin/map2vmf.sh`

    Parameters: `${MAP_BASE_NAME}.vmf -lightmapscale <whatever> ${MAP_FULL_NAME}`

6. Add a Run Tool Command for VBSP:

    Tool: `${GAME_DIR_PATH}/../bin/vbsp.sh`

    Parameters: `${MAP_BASE_NAME}.vmf`

7. Repeat step 6 for VVIS and VRAD

8. Add one last Copy File command to move the BSP into the maps folder:

    Source: `${GAME_DIR_PATH}/../bin/${MAP_BASE_NAME}.bsp`

    Target: `${GAME_DIR_PATH}/maps/${MAP_BASE_NAME}.bsp`

9. If you want, you can add more commands to remove the unneeded `.prt`, `.log`, `.map`, etc. files. Just don't remove the original map file or you will lose all your work.

10. Use the Test button to make sure it is running the correct commands, then once you are satisfied, you can use the Compile button to compile your map. Load it up in game as normal and have fun mapping.