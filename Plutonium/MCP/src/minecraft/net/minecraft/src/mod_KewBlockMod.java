//
// This is Kew's first mod.
//
// We wanted to do CowBell, but we are following the tutorial at
// http://www.youtube.com/watch?v=Yy2QAWOPiBI
//

package net.minecraft.src;

import java.util.Random;


// This is the actual  block mod
public class mod_KewBlockMod extends BaseMod
{
	
	// Properties

	// The actual block
	public static final Block plutonium;
	
	// The Item...
	public static Item plutoniumBlock;
	
	// Constructors	
	static 
	{
		System.out.println("Kew's mod running static constructor.");
		plutonium = new mod_KewBlock();				
		plutoniumBlock = (new Item(150)).setFull3D().setItemName("plutoniumBlock");				
	}
	
    public mod_KewBlockMod()
    {
    	//super();
    	
    	System.out.println("Kew's mod running constructor.");
    }

    // Methods
    
    //@Override
    public String getVersion()
    {
    	return "1.0.0";
    }

	//@Override
	public void load() 
	{
			System.out.println("Kew's mod running load().");
			
	    	ModLoader.RegisterBlock(plutonium);
	    	ModLoader.AddName(plutonium, "Plutonium");    	                                                                       
	    	plutonium.blockIndexInTexture = ModLoader.addOverride("/terrain.png","/plutonium_texture.png");
	
	    	ModLoader.AddName(plutoniumBlock, "Plutonium Block");
	    	
	    	plutoniumBlock.iconIndex = ModLoader.addOverride("/gui/items.png", "/plutonium_block.png");
	
	    	//
	    	// Here we define our recipe
	    	//
	    	
	    	
	    	// This is the pattern to put into the crafting table..
	    	Object[] recipe =  new Object[] {
	    			"PPP",
	    			"PPP",
	    			"PPP",
	    			
	    			Character.valueOf('P'),plutonium
	    	};

	    	// ... and this is what we build.
	    	ItemStack stack = new ItemStack(plutoniumBlock,1);
	    	
	    	// Add the recipe to Minecraft.
	    	
	    	ModLoader.AddRecipe(stack,recipe);
	}
	
	// Generate custom blocks in the world
    public void GenerateSurface(World world, Random random, int x, int z)
    {
    	//System.out.println("Kew's mod GenerateSurface.");
    	
    	for(int a = 0;a < 100;a++)
    	{
    		int posX = x + random.nextInt(16); 
    		int posY = random.nextInt(128);
    		int posZ = z + random.nextInt(16);
    		
    		//System.out.println(String.format("Place plutonium %s %s %s.", posX, posY, posZ));
    		
    		(new WorldGenMinable(plutonium.blockID, 32)).generate(world, random, posX, posY, posZ);
    	}
    	
    }

}
