package com.ferreusveritas.dynamictrees.proxy;

import com.ferreusveritas.dynamictrees.api.backport.EnumParticleTypes;
import com.ferreusveritas.dynamictrees.event.ClientEventHandler;
import com.ferreusveritas.dynamictrees.renderers.RendererBonsai;
import com.ferreusveritas.dynamictrees.renderers.RendererBranch;
import com.ferreusveritas.dynamictrees.renderers.RendererRootyDirt;
import com.ferreusveritas.dynamictrees.renderers.RendererSapling;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityBlockDustFX;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy extends CommonProxy {
	
	@Override
	public void preInit() {
		super.preInit();
	}
	
	@Override
	public void init() {
		super.init();
		
		RenderingRegistry.registerBlockHandler(new RendererBonsai());
		RenderingRegistry.registerBlockHandler(new RendererBranch());
		RenderingRegistry.registerBlockHandler(new RendererRootyDirt());
		RenderingRegistry.registerBlockHandler(new RendererSapling());
	}
	
	@Override
	public void registerModels() {
		//1.7.10 Does not register models
	}
	
	void makePlantsBlue() {
		//1.7.10 Does not register BlockColorHandlers
	}
	
	@Override
	public EntityPlayer getClientPlayer() {
		return Minecraft.getMinecraft().thePlayer;
	}
	
	@Override
	public World getClientWorld() {
		return Minecraft.getMinecraft().theWorld;
	}
	
	@Override 
	public void registerEventHandlers() {
		super.registerEventHandlers();//Registers Common Handlers
		ClientEventHandler ev = new ClientEventHandler();
		FMLCommonHandler.instance().bus().register(ev);
		MinecraftForge.EVENT_BUS.register(ev);
	}
	
	//1.7.10 Does not register BlockColorHandlers
	
	///////////////////////////////////////////
	// PARTICLES
	///////////////////////////////////////////
	
	@Override
	public void addDustParticle(World world, double fx, double fy, double fz, double mx, double my, double mz, int x, int y, int z, Block block, int metadata) {
		EntityFX entityfx = (new EntityBlockDustFX(world, fx, fy, fz, mx, my, mz, block, metadata)).applyColourMultiplier(x, y, z);
		Minecraft.getMinecraft().effectRenderer.addEffect(entityfx);
	}
	
	/**
	 * Not strictly necessary. But adds a little more isolation to the server for particle effects
	 */
	@Override
	public void spawnParticle(World world, EnumParticleTypes particleType, double x, double y, double z, double mx, double my, double mz) {
		world.spawnParticle(particleType.getName(), x, y, z, mx, my, mz);
	}
	
}