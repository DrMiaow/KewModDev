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
		super(200, 0, Material.rock);
		setHardness(1.1F);
		setResistance(10F);
		setStepSound(Block.soundMetalFootstep);
		setBlockName("cowbell");
		// setLightValue(1);
	}

	public int getBlockTextureFromSide(int i)
	{
		if (i == 1)
		{
			// Top
			return Block.blockGold.getBlockTextureFromSide(0);
		}
		if (i == 0)
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

	// This handles the redstone power
	public void onNeighborBlockChange(World world, int i, int j, int k, int l)
	{		
		// If block is redstone powered
		if (l > 0 && Block.blocksList[l].canProvidePower() && world.isBlockIndirectlyGettingPowered(i, j, k))
		{
			generateCow(world, i, j, k, null);		
		}
	}

	//
	// What happens when someone right clicks on the block
	//
	public boolean blockActivated(World world, int i, int j, int k, EntityPlayer entityplayer)
	{
		generateCow(world, i, j, k, entityplayer);
		return true;
	}
	
	
	
	
	
	
	// ThiS will generate our cow
	private void generateCow(World world, int i, int j, int k,EntityPlayer entityplayer) 
	{
		// Play a sound (like a cowbell)
		
		//world.playSoundAtEntity(entityplayer, "random.levelup", 1.0F, 1.0F);		
		
		world.playSoundEffect(i, j, k, "random.levelup", 1.0F, 1.0F);

		// The cow we are trying to create
		EntityLiving cow = null;

		// Roll the dice
		int roll = world.rand.nextInt(20);

		if ((roll == 2) || (roll == 3))
		{
			if (entityplayer != null)
			{
				entityplayer.addChatMessage("OMG!!! MooShroom!!!!");
			}

			// Set entity to a MooShroom
			cow = (EntityLiving) EntityList.createEntityByName("MushroomCow", world);
		}
		else if ((roll == 4) || (roll == 5) || (roll == 6))
		{
			if (entityplayer != null)
			{
				entityplayer.addChatMessage("MY BABY HAS MUSHROOMS ON IT'S BACK!!!!");
			}
			

			// Set entity to a MooShroom
			cow = (EntityLiving) EntityList.createEntityByName("MushroomCow", world);

			// Make it a baby
			((EntityAnimal) cow).setGrowingAge(-24000);
		}
		else if (roll == 7)
		{
			if (entityplayer != null)
			{
				entityplayer.addChatMessage("It's a boy!!!");
			}

			// Set entity to a MooShroom
			cow = (EntityLiving) EntityList.createEntityByName("Cow", world);

			// Make it a baby
			((EntityAnimal) cow).setGrowingAge(-24000);
		}
		else
		{
			if (entityplayer != null)
			{
				entityplayer.addChatMessage("We give a cow... man. :)");
			}
			

			// Set entity to a Cow
			cow = (EntityLiving) EntityList.createEntityByName("Cow", world);
		}

		// System.out.println(String.format("Block location %s %s %s.",i,j,k));
		// System.out.println(String.format("Player location %s %s %s.",entityplayer.posX,entityplayer.posY,entityplayer.posZ));

		// Set the cow to be at the block
		double x = (double) i;
		double y = (double) j;
		double z = (double) k;

		///.. but if we know where the player is... 
		if (entityplayer != null)
		{
			// .. calculate put the cow half way between the block and the player.
			x = ((double) i + entityplayer.posX) / 2.0d;
			y = ((double) j + entityplayer.posY) / 2.0d;
			z = ((double) k + entityplayer.posZ) / 2.0d;
		}
		
		
		// But then go up 2 blocks to put the cow in the air..
		y += 2;

		// Place cow in world at location
		cow.setLocationAndAngles(x, y, z, 0F, 0F);

		// Create random 3d vector with values from -0.5 to 0.5
		double dx = world.rand.nextDouble() - 0.5;
		double dy = world.rand.nextDouble() - 0.5;
		double dz = world.rand.nextDouble() - 0.5;

		// This is how fast we want to go..
		double velocity = 1.0;

		// Multiply (scale) that vector by velocity.
		dx *= velocity;
		dy *= velocity;
		dz *= velocity;

		// Set the speed of the cow to the 3d vector
		cow.setVelocity(dx, dy, dz);

		// Add the entity to the world
		world.joinEntityInSurroundings(cow);
	}

}
