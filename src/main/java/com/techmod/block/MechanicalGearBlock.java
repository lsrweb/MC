package com.techmod.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class MechanicalGearBlock extends HorizontalDirectionalBlock {
    public static final BooleanProperty CONNECTED = BooleanProperty.create("connected");

    private static final VoxelShape NORTH_SOUTH_SHAPE = Block.box(2.0D, 2.0D, 6.0D, 14.0D, 14.0D, 10.0D);
    private static final VoxelShape EAST_WEST_SHAPE = Block.box(6.0D, 2.0D, 2.0D, 10.0D, 14.0D, 14.0D);

    public MechanicalGearBlock() {
        super(BlockBehaviour.Properties.of()
                .strength(2.5F, 4.0F)
                .noOcclusion()
                .requiresCorrectToolForDrops());
        registerDefaultState(stateDefinition.any()
                .setValue(FACING, Direction.NORTH)
                .setValue(CONNECTED, false));
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Direction facing = context.getHorizontalDirection().getOpposite();
        BlockState state = defaultBlockState().setValue(FACING, facing);
        return state.setValue(CONNECTED, isConnectedToEngine(context.getLevel(), context.getClickedPos(), facing));
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState,
                                  LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        return state.setValue(CONNECTED, isConnectedToEngine(level, pos, state.getValue(FACING)));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        Direction facing = state.getValue(FACING);
        if (facing == Direction.EAST || facing == Direction.WEST) {
            return EAST_WEST_SHAPE;
        }

        return NORTH_SOUTH_SHAPE;
    }

    @Override
    public boolean useShapeForLightOcclusion(BlockState state) {
        return true;
    }

    @Override
    public VoxelShape getOcclusionShape(BlockState state, BlockGetter level, BlockPos pos) {
        return Shapes.empty();
    }

    private static boolean isConnectedToEngine(LevelAccessor level, BlockPos pos, Direction facing) {
        return isEnginePort(level, pos.relative(facing), facing.getOpposite())
                || isEnginePort(level, pos.relative(facing.getOpposite()), facing);
    }

    private static boolean isEnginePort(LevelAccessor level, BlockPos enginePos, Direction gearSide) {
        BlockState engineState = level.getBlockState(enginePos);
        return engineState.getBlock() instanceof CombustionEngineBlock engine
                && engine.hasGearPort(engineState, gearSide);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, CONNECTED);
    }
}
