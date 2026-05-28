package com.jm1133.scrappedfools.entity.custom;

import java.util.EnumSet;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.ClimbOnTopOfPowderSnowGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.InfestedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.event.EventHooks;

public class RedstoneBug extends Monster {

    public RedstoneBug(EntityType<? extends RedstoneBug> entityType, Level level) {
        super(entityType, level);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(1, new ClimbOnTopOfPowderSnowGoal(this, this.level()));
        this.goalSelector.addGoal(4, new MeleeAttackGoal(this, (double)1.0F, false));
        this.goalSelector.addGoal(5, new SilverfishMergeWithStoneGoal(this));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this, new Class[0])).setAlertOthers(new Class[0]));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal(this, Player.class, true));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH, (double)8.0F).add(Attributes.MOVEMENT_SPEED, (double)0.25F).add(Attributes.ATTACK_DAMAGE, (double)1.0F);
    }

    protected Entity.MovementEmission getMovementEmission() {
        return MovementEmission.EVENTS;
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.SILVERFISH_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return SoundEvents.SILVERFISH_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.SILVERFISH_DEATH;
    }

    protected void playStepSound(BlockPos pos, BlockState block) {
        this.playSound(SoundEvents.SILVERFISH_STEP, 0.15F, 1.0F);
    }

    public void tick() {
        this.yBodyRot = this.getYRot();
        super.tick();
    }

    public void setYBodyRot(float offset) {
        this.setYRot(offset);
        super.setYBodyRot(offset);
    }

    public float getWalkTargetValue(BlockPos pos, LevelReader level) {
        return InfestedBlock.isCompatibleHostBlock(level.getBlockState(pos.below())) ? 10.0F : super.getWalkTargetValue(pos, level);
    }

    public static boolean checkSilverfishSpawnRules(EntityType<RedstoneBug> silverfish, LevelAccessor level, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
        if (checkAnyLightMonsterSpawnRules(silverfish, level, spawnType, pos, random)) {
            Player player = level.getNearestPlayer((double)pos.getX() + (double)0.5F, (double)pos.getY() + (double)0.5F, (double)pos.getZ() + (double)0.5F, (double)5.0F, true);
            return player == null;
        } else {
            return false;
        }
    }

    static class SilverfishMergeWithStoneGoal extends RandomStrollGoal {
        @Nullable
        private Direction selectedDirection;
        private boolean doMerge;

        public SilverfishMergeWithStoneGoal(RedstoneBug redstoneBug) {
            super(redstoneBug, (double) 1.0F, 10);
            this.setFlags(EnumSet.of(Flag.MOVE));
        }

        public boolean canUse() {
            if (this.mob.getTarget() != null) {
                return false;
            } else if (!this.mob.getNavigation().isDone()) {
                return false;
            } else {
                RandomSource randomsource = this.mob.getRandom();
                if (EventHooks.canEntityGrief(this.mob.level(), this.mob) && randomsource.nextInt(reducedTickDelay(10)) == 0) {
                    this.selectedDirection = Direction.getRandom(randomsource);
                    BlockPos blockpos = BlockPos.containing(this.mob.getX(), this.mob.getY() + (double) 0.5F, this.mob.getZ()).relative(this.selectedDirection);
                    BlockState blockstate = this.mob.level().getBlockState(blockpos);
                }

                this.doMerge = false;
                return super.canUse();
            }
        }

        public boolean canContinueToUse() {
            return this.doMerge ? false : super.canContinueToUse();
        }
    }
}
