package com.techmod.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import org.jetbrains.annotations.Nullable;

public class CombustionEngineBlock extends HorizontalDirectionalBlock {
    public CombustionEngineBlock() {
        super(BlockBehaviour.Properties.of()
                .strength(4.0F, 6.0F)
                .requiresCorrectToolForDrops());
        registerDefaultState(stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState,
                                  LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        return super.updateShape(state, direction, neighborState, level, pos, neighborPos);
    }

    public boolean hasGearPort(BlockState state, Direction side) {
        Direction facing = state.getValue(FACING);
        return side == facing.getClockWise() || side == facing.getCounterClockWise();
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }
}
