//
// This is Kew's second mod.
//
// Name : Cowbell.
//
// Requirements : Click - Plays sound. Spawns a cow. (1/10 chance of MooShroom). 
// 
//

package net.minecraft.src;

import java.util.Random;


public class mod_CowbellBlock extends Block
{
	
	 protected mod_CowbellBlock()
	 {
	        super(125, 0, Material.rock);
	        setHardness(1.1F);
	        setResistance(10F);
	        setStepSound(Block.soundMetalFootstep);
	        setBlockName("cowbell");
	        //setLightValue(1);
	 }
	 
	 
	  public int getBlockTextureFromSide(int i)
	    {
	        if(i == 1)
	        {
	        	// Top
	        	return Block.blockGold.getBlockTextureFromSide(0);
	        }
	        if(i == 0)
	        {
	        	// Bottom
	            return Block.portal.blockIndexInTexture;
	        }
	        else
	        {
	        	// 2-5 sides...
	            return blockIndexInTexture;
	        }
	    }
	  
	  
    public void onNeighborBlockChange(World world, int i, int j, int k, int l)
    {
        if (l > 0 && Block.blocksList[l].canProvidePower() && world.isBlockIndirectlyGettingPowered(i, j, k))
        {
         // Play a sound (like a cowbell)
   		 //world.playSoundAtEntity(entityplayer, "random.levelup", 1.0F, 1.0F);
   		    		 
   		  world.playSoundEffect(i,j,k, "random.levelup", 1.0F, 1.0F);
   		    		 
   		 // The cow we are trying to create
   		 EntityLiving cow = null;
   	         
   		 // Roll the dice 
   	     int roll = world.rand.nextInt(10);
   	         
    	    // Kew likes 2 for a MooShroom, convention be damned..
            if (roll == 2) 
            {
            	//	Set entity to a MooShroom
           	 	cow = (EntityLiving)EntityList.createEntityInWorld("MushroomCow", world);
            }
            else
            {	        	 
           	 	// Set entity to a Cow
           	 	cow = (EntityLiving)EntityList.createEntityInWorld("Cow", world);	        	 
            }
            
            
            //System.out.println(String.format("Block location %s %s %s.",i,j,k));
            //System.out.println(String.format("Player location %s %s %s.",entityplayer.posX,entityplayer.posY,entityplayer.posZ));
            
            // calculate 3d point half way between the block and the player. 
            double x = ((double)i)/1.0d;	         
            double y = ((double)j)/1.0d;	         
            double z = ((double)k)/1.0d;
            
            // go up 120 blocks
            y += 2;
            
            // Place pig in world at location
            cow.setLocationAndAngles(x, y, z, 0F, 0F);
            
            // Create random 3d vector with values from -0.5 to 0.5
            double dx = world.rand.nextDouble() - 0.5;
            double dy = world.rand.nextDouble() - 0.5;
            double dz = world.rand.nextDouble() - 0.5;
            
            // How fast we want to go..
            double velocity = 1.0;
            
            // Multiply (scale) that vector by velocity.
            dx *= velocity;
            dy *= velocity;
            dz *= velocity;

            // Set the speed
            cow.setVelocity(dx, dy, dz);
            
            // Add the entity to the world
            world.joinEntityInSurroundings(cow);	
        }
    }
	  

	 //
	 // What happens when someone right clicks on the block
	 //
	 public boolean blockActivated(World world, int i, int j, int k, EntityPlayer entityplayer)
	 {

		 // Play a sound (like a cowbell)
		 world.playSoundAtEntity(entityplayer, "random.levelup", 1.0F, 1.0F);
		 
		 // The cow we are trying to create
		 EntityLiving cow = null;
	         
		 // Roll the dice 
	     int roll = world.rand.nextInt(10);
	         
 	     // Kew likes 2 for a MooShroom, convention be damned..
         if (roll == 2) 
         {
        	 entityplayer.addChatMessage("OMG!!! MooShroom!!!!");
        	 
        	 //	Set entity to a MooShroom
        	 cow = (EntityLiving)EntityList.createEntityInWorld("MushroomCow", world);
         }
         else
         {	        	 
        	 entityplayer.addChatMessage("We give a cow... man. :)");
        	 
        	 // Set entity to a Cow
        	 cow = (EntityLiving)EntityList.createEntityInWorld("Cow", world);	        	 
         }
         
         
         //System.out.println(String.format("Block location %s %s %s.",i,j,k));
         //System.out.println(String.format("Player location %s %s %s.",entityplayer.posX,entityplayer.posY,entityplayer.posZ));
         
         // calculate 3d point half way between the block and the player. 
         double x = ((double)i + entityplayer.posX)/2.0d;	         
         double y = ((double)j + entityplayer.posY)/2.0d;	         
         double z = ((double)k + entityplayer.posZ)/2.0d;
         
         // go up 120 blocks
         y += 2;
         
         // Place pig in world at location
         cow.setLocationAndAngles(x, y, z, 0F, 0F);
         
         // Create random 3d vector with values from -0.5 to 0.5
         double dx = world.rand.nextDouble() - 0.5;
         double dy = world.rand.nextDouble() - 0.5;
         double dz = world.rand.nextDouble() - 0.5;
         
         // How fast we want to go..
         double velocity = 1.0;
         
         // Multiply (scale) that vector by velocity.
         dx *= velocity;
         dy *= velocity;
         dz *= velocity;

         // Set the speed
         cow.setVelocity(dx, dy, dz);
         
         // Add the entity to the world
         world.joinEntityInSurroundings(cow);
     		
		 return true;
	 }
	 
        	 
}

