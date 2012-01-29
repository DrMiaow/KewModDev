//
// This is Kew's first mod.
//
// We wanted to do CowBell, but we are following the tutorial at
// http://www.youtube.com/watch?v=Yy2QAWOPiBI
//

package net.minecraft.src;

import java.util.Random;


public class mod_KewBlock extends Block
{
	
	 protected mod_KewBlock()
	 {
	        super(125, 0, Material.rock);
	        setHardness(1.1F);
	        setResistance(10F);
	        setStepSound(Block.soundStoneFootstep);
	        setBlockName("plutonium");
	        setLightValue(1);
	        
	        //setTickOnLoad(true);
	 }
	 
	 

	//
	// When entity is walking on block?
	// 
	public void onEntityWalking(World world, int i, int j, int k, Entity entity)
	{
		System.out.println(String.format("Kew's block is being walked on %s %s %s Entity %s '%s'",i,j,k,entity.entityId,entity.getEntityString()));
		
		//entity.attackEntityFrom(DamageSource.cactus, 5);
	}

	//
	// Block has been placed
	//
	public void onBlockPlaced(World world, int i, int j, int k, int l)
	{
		System.out.println("Kew's block has been placed.");
	}
	
	public void onBlockClicked(World world, int i, int j, int k, EntityPlayer entityplayer)
	{
		 System.out.println("Kew's block has been clicked.");
	}

	 //
	 // What happens when someone right clicks on the block
	 //
	 public boolean blockActivated(World world, int i, int j, int k, EntityPlayer entityplayer)
	 {
		 System.out.println("Kew's block has been activated.");
		 
		 world.playSoundAtEntity(entityplayer, "random.levelup", 1.0F, 1.0F);
		 //world.playSoundAtEntity(entityplayer, "cowbell.cowbell", 1.0F, 1.2F);
    	
		 //ModLoader.getMinecraftInstance().thePlayer.addChatMessage("Summoned a pig!");
		 
		 
		 
		 // 0..255
		 
		 
		 //for(int p = 0; p < 1; p++)
		 //{
			 // Create a variable that will be a reference to the Cow we create
	         EntityLiving cow = null;
	         
	         int roll = world.rand.nextInt(10);
	         
	         
	         if (roll == 2) //Kew likes 2 :)
	         {
	        	 entityplayer.addChatMessage("OMG!!! MooShroom!!!!");
	        	 
	        	 //	Set entity to a MooShroom
	        	 cow = (EntityLiving)EntityList.createEntityInWorld("MushroomCow", entityplayer.worldObj);
	         }
	         else
	         {	        	 
	        	 entityplayer.addChatMessage("We give a cow... man. :)");
	        	 
	        	 // Set entity to a Cow
	        	 cow = (EntityLiving)EntityList.createEntityInWorld("Cow", entityplayer.worldObj);	        	 
	         }
	         
	         
	         
	         
	         //System.out.println(String.format("Block location %s %s %s.",i,j,k));
	         //System.out.println(String.format("Player location %s %s %s.",entityplayer.posX,entityplayer.posY,entityplayer.posZ));
	         
	         // calculate 3d point half way between the block and the player. 
	         double x = ((double)i + entityplayer.posX)/2.0d;	         
	         double y = ((double)j + entityplayer.posY)/2.0d;	         
	         double z = ((double)k + entityplayer.posZ)/2.0d;

	         //double x = (double)i;
	         //double y = (double)j+ 1.0d;
	         //double z = (double)k;
	         
	         //double x = (double)i + entityplayer.posX;
	         //double y = (double)j + entityplayer.posY + 1.0d;
	         //double z = (double)k + entityplayer.posZ;
	         
	         // go up 120 blocks
	         y += 2;
	         
	         //0.4568785734297859345354
	         
	         //0.00334234253454
	         
	         //0.999998954968895
	         
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
	         
	         // Add the pig to the world
	         entityplayer.worldObj.joinEntityInSurroundings(cow);
		 //}
         
         //entityplayer.worldObj.entityJoinedWorld(entityliving);
         //entityplayer.swingItem();
     		
		 return false;
	 }
    
    
    //
    // Make it smoke
    //
    
    public void randomDisplayTick(World world, int i, int j, int k, Random random)
    {
    	// If there is air above us.
    	if(world.isAirBlock(i, j + 1, k))
    	{    		 
	        double d = (float)i + random.nextFloat();
	        double d1 = (float)j + 0.8F;
	        double d2 = (float)k + random.nextFloat();
	        double d3 = 0.0D;
	        double d4 = 0.0D;
	        double d5 = 0.0D;
	        world.spawnParticle("smoke", d, d1, d2, d3, d4, d5);
    	}
    }
	 
}

