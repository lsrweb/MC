# Core Tech 开发规范

## 新增内容准入标准

新增任何方块、物品、机器、管道或矿物，必须同时满足：

- 独立命名。
- 独立 class，除非只是纯材料 item。
- 模型文件。
- 贴图文件。
- 中英文语言文件。
- 配方或用途。
- 文档说明。
- 如果进入世界：掉落、工具等级、生成或获取路径。

## 机器新增模板

每台机器至少包含：

- `block/<Machine>Block.java`
- `blockentity/<Machine>BlockEntity.java`
- `menu/<Machine>Menu.java`
- `client/screen/<Machine>Screen.java`
- `models/block/<machine>.json`
- `models/item/<machine>.json`
- `textures/block/<machine>_front.png`
- `textures/block/<machine>_side.png`
- `textures/block/<machine>_top.png`
- `loot_tables/blocks/<machine>.json`
- 文档更新：`docs/systems/machines.md`

## 管道新增模板

每种管道至少包含：

- 管道方块类。
- 连接状态定义。
- 多部件模型或动态模型。
- 传输逻辑。
- 配置工具或交互方式。
- 文档更新：`docs/systems/pipes.md`

