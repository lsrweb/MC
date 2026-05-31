import { Navigate, NavLink, Route, Routes } from 'react-router-dom'
import './App.css'

const implementedMinerals = [
  {
    name: '锡',
    icon: '/textures/block/tin_ore.png',
    range: 'Y -16 到 96',
    tool: '铁镐及以上',
    rarity: '常见',
    traits: '低熔点、容易加工，适合作为早期金属体系。',
    uses: '制作焊料、青铜相关材料、早期机器外壳和基础加工部件。',
    note: '适合放在玩家进入科技线的第一阶段。',
  },
  {
    name: '铝',
    icon: '/textures/block/aluminum_ore.png',
    range: 'Y 32 到 128',
    tool: '铁镐及以上',
    rarity: '常见',
    traits: '轻质、导热，适合结构件和散热件。',
    uses: '制作轻型框架、散热片、外壳、低级机器结构件。',
    note: '更多分布在较高地层，鼓励玩家探索山地和高海拔区域。',
  },
  {
    name: '铅',
    icon: '/textures/block/lead_ore.png',
    range: 'Y -48 到 32',
    tool: '铁镐及以上',
    rarity: '较少',
    traits: '密度高、屏蔽性强，适合电池和防护。',
    uses: '制作电池芯、辐射屏蔽、重型机器外壳。',
    note: '后续会和铀矿、防护服、反应堆系统绑定。',
  },
  {
    name: '银',
    icon: '/textures/block/silver_ore.png',
    range: 'Y -64 到 16',
    tool: '铁镐及以上',
    rarity: '稀有',
    traits: '导电性高，适合精密电路和高级接点。',
    uses: '制作高级电路、高级线缆、传感器和控制元件。',
    note: '产量不高，定位为中期电路材料。',
  },
  {
    name: '铀',
    icon: '/textures/block/uranium_ore.png',
    range: 'Y -64 到 -16',
    tool: '铁镐及以上',
    rarity: '极稀有',
    traits: '放射性，高能量密度，不能当普通矿物处理。',
    uses: '后期反应堆燃料、核能链、危险能源实验。',
    note: '持有和接触都应该有风险，后续需要防护装备和安全容器。',
    hazard: true,
  },
]

const plannedMinerals = [
  ['镍', '中期合金', 'Y -32 到 48', '铁镐及以上', '制作不锈钢、耐热合金、电机轴承。'],
  ['锌', '合金基础', 'Y -16 到 80', '铁镐及以上', '和铜组成黄铜，用于精密齿轮、仪表和管件。'],
  ['钛', '高级结构', 'Y -48 到 16', '钻石镐及以上', '制作轻量高强度机器框架、涡轮部件和高级外壳。'],
  ['钨', '高温材料', 'Y -64 到 -24', '钻石镐及以上', '制作高温炉、硬质钻头、高级粉碎部件。'],
  ['锂', '储能材料', 'Y -32 到 32', '铁镐及以上', '制作高容量电池、移动电源和储能升级。'],
  ['硫', '化工材料', 'Y -16 到 64', '石镐及以上', '用于橡胶处理、火药改良、化工反应。'],
  ['铂', '高级催化', 'Y -64 到 -32', '钻石镐及以上', '制作催化器、顶级电路触点和高阶化工机器。'],
]

const vanillaOreUses = [
  ['煤炭', '燃料起点', '燃煤发电机、焦炭、早期热源。'],
  ['铁', '基础结构', '铁板、齿轮、机器外壳、工具级零件。'],
  ['铜', '导线核心', '铜线、线圈、电机、低压电缆、早期电路。'],
  ['金', '高导电接点', '高速电路、精密触点、低损耗升级件。'],
  ['红石', '控制信号', '电路板、传感器、逻辑控制、机器开关。'],
  ['青金石', '校准材料', '电路蚀刻、机器升级、数据存储材料。'],
  ['钻石', '高强工具', '高级钻头、切割刀头、耐磨机器部件。'],
  ['绿宝石', '交易与校准', '村民贸易科技线、精密校准核心。'],
  ['下界石英', '硅与晶圆', '硅晶圆、处理器、传感器镜片。'],
  ['远古残骸', '终局材料', '高温外壳、反应堆防护、终局机器框架。'],
]

const blocks = [
  {
    name: '核心工作台',
    icon: '/textures/block/core_workbench_front.png',
    category: '核心方块',
    obtain: '通过基础电路、机械齿轮和铁板合成。',
    use: '打开科技线的第一张工作台。用于 3x3 科技合成，并承接后续机器、管道和升级件。',
    tip: '这是进入模组的第一块方块。后续所有机器都应该围绕它展开。',
  },
  ...implementedMinerals.map((mineral) => ({
    name: `${mineral.name}矿石`,
    icon: mineral.icon,
    category: '矿石',
    obtain: `${mineral.range} 生成，${mineral.tool}采掘。`,
    use: mineral.uses,
    tip: mineral.note,
  })),
]

const parts = [
  ['铁板', '/textures/item/iron_plate.png', '基础结构件，用于工作台、机器外壳和早期机械部件。'],
  ['铜线', '/textures/item/copper_wire.png', '电力和信号的基础材料，用于电路、电池和线缆。'],
  ['机械齿轮', '/textures/item/machine_gear.png', '机械传动基础件，用于粉碎机、泵、电机和加工机器。'],
  ['硅晶圆', '/textures/item/silicon_wafer.png', '电路核心材料，来自石英加工，后续用于处理器。'],
  ['电池芯', '/textures/item/battery_cell.png', '早期储能材料，用于基础电路、储能单元和移动供电。'],
  ['基础电路', '/textures/item/basic_circuit.png', '所有自动化机器的控制核心，也是核心工作台的关键材料。'],
]

const machines = [
  ['核心工作台', '入门', '科技合成入口，玩家第一次接触模组的核心方块。'],
  ['燃煤发电机', '早期能源', '消耗煤炭或木炭产生能量，给第一批机器供电。'],
  ['粉碎机', '矿物加工', '把矿石粉碎成粉，提高产出，并为洗矿、筛选做准备。'],
  ['电力熔炉', '自动熔炼', '消耗能量完成熔炼，比普通熔炉更稳定。'],
  ['储能单元', '能源缓存', '储存能量，减少机器断电，作为能源网络节点。'],
  ['组装台', '中期制造', '制作复杂机器、多零件配方和高级电路。'],
  ['化工反应釜', '化工线', '处理硫、橡胶、酸液、催化剂等材料。'],
  ['反应堆核心', '后期能源', '使用铀燃料提供大量能量，但必须配套冷却和辐射防护。'],
]

const pipes = [
  ['物品管道', '搬运物品', '连接箱子和机器，支持过滤、优先级和抽取方向。'],
  ['低压电缆', '传输能量', '早期能量传输线，适合短距离机器网络。'],
  ['中压电缆', '传输能量', '中期电网骨干，承载更多机器。'],
  ['流体管道', '搬运液体', '用于冷却液、蒸汽、酸液和化工液体。'],
  ['物流节点', '网络控制', '管理一段管网的白名单、方向和红石控制。'],
]

const docs = [
  ['入门路线', '先制作核心工作台，再进入发电、粉碎、熔炼、储能。'],
  ['矿物路线', '锡和铝偏早期，铅和银偏中期，铀偏后期危险能源。'],
  ['机器路线', '先做发电和加工，再做管道、储能、自动化和核能。'],
  ['安全路线', '铀矿、反应堆、化工液体都需要后续防护系统。'],
]

const navItems = [
  ['/', '百科总览'],
  ['/blocks', '方块'],
  ['/minerals', '新增矿物'],
  ['/vanilla-ores', '原版矿物'],
  ['/parts', '材料部件'],
  ['/machines', '机器'],
  ['/pipes', '管道'],
  ['/guide', '游玩路线'],
]

function Texture({ src, name }) {
  return (
    <span className="texture-frame">
      <img src={src} alt={name} />
    </span>
  )
}

function PageHeader({ title, text }) {
  return (
    <div className="page-header">
      <h1>{title}</h1>
      <p>{text}</p>
    </div>
  )
}

function InfoTable({ columns, rows }) {
  return (
    <div className="table-wrap">
      <table>
        <thead>
          <tr>{columns.map((column) => <th key={column}>{column}</th>)}</tr>
        </thead>
        <tbody>
          {rows.map((row) => (
            <tr key={row.join('|')}>
              {row.map((cell) => <td key={cell}>{cell}</td>)}
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  )
}

function OverviewPage() {
  return (
    <section>
      <PageHeader
        title="核心科技百科"
        text="这是给玩家看的模组百科。这里只解释怎么获得、有什么用、危险点在哪里，以及下一步应该做什么。"
      />
      <div className="hero-panel">
        <Texture src="/textures/block/core_workbench_front.png" name="核心工作台" />
        <div>
          <h2>第一目标：制作核心工作台</h2>
          <p>核心工作台是整条科技线的入口。玩家先收集铁、铜、石英和早期矿物，制作基础电路和机械齿轮，再解锁机器系统。</p>
        </div>
      </div>
      <div className="summary-grid">
        {[
          ['新增矿物', '锡、铝、铅、银、铀已经进入矿物线；镍、锌、钛、钨、锂、硫、铂作为后续扩展。'],
          ['机器路线', '发电、粉碎、熔炼、储能、组装、化工、核能按阶段推进。'],
          ['管道路线', '物品、能量、流体三类管道分开，后续用物流节点管理网络。'],
          ['安全路线', '铀和反应堆不能随便处理，需要铅屏蔽、防护装备和冷却系统。'],
        ].map(([title, text]) => (
          <article className="simple-card" key={title}>
            <h3>{title}</h3>
            <p>{text}</p>
          </article>
        ))}
      </div>
    </section>
  )
}

function BlocksPage() {
  return (
    <section>
      <PageHeader title="方块" text="这里展示玩家能放置、采掘或交互的方块。每个方块都用玩法语言说明，不展示内部实现细节。" />
      <div className="card-grid">
        {blocks.map((block) => (
          <article className="content-card" key={block.name}>
            <div className="card-title-row">
              <Texture src={block.icon} name={block.name} />
              <div>
                <h3>{block.name}</h3>
                <p>{block.category}</p>
              </div>
            </div>
            <dl>
              <dt>获取</dt>
              <dd>{block.obtain}</dd>
              <dt>用途</dt>
              <dd>{block.use}</dd>
              <dt>提示</dt>
              <dd>{block.tip}</dd>
            </dl>
          </article>
        ))}
      </div>
    </section>
  )
}

function MineralsPage() {
  return (
    <section>
      <PageHeader title="新增矿物" text="新增矿物不只是换个矿石贴图，每种矿物都要有采掘门槛、生成区间、材料定位和后续用途。" />
      <div className="card-grid">
        {implementedMinerals.map((mineral) => (
          <article className="content-card" key={mineral.name}>
            <div className="card-title-row">
              <Texture src={mineral.icon} name={`${mineral.name}矿石`} />
              <div>
                <h3>{mineral.name}矿石</h3>
                <p>{mineral.range} · {mineral.rarity}</p>
              </div>
            </div>
            <dl>
              <dt>采掘</dt>
              <dd>{mineral.tool}</dd>
              <dt>特性</dt>
              <dd>{mineral.traits}</dd>
              <dt>用途</dt>
              <dd>{mineral.uses}</dd>
            </dl>
            {mineral.hazard && <strong className="hazard">危险材料：需要防护和安全容器</strong>}
          </article>
        ))}
      </div>
      <h2 className="section-title">后续扩展矿物</h2>
      <InfoTable columns={['矿物', '定位', '生成', '工具', '用途']} rows={plannedMinerals} />
    </section>
  )
}

function VanillaOresPage() {
  return (
    <section>
      <PageHeader title="原版矿物扩展" text="原版矿物也应该进入科技体系。玩家熟悉的矿物会获得更明确的工业用途。" />
      <InfoTable columns={['矿物', '科技定位', '扩展用途']} rows={vanillaOreUses} />
    </section>
  )
}

function PartsPage() {
  return (
    <section>
      <PageHeader title="材料部件" text="部件页解释材料能做什么，玩家不需要知道内部注册名，只需要知道它在科技线中的位置。" />
      <div className="parts-grid">
        {parts.map(([name, icon, text]) => (
          <article className="content-card" key={name}>
            <Texture src={icon} name={name} />
            <h3>{name}</h3>
            <p>{text}</p>
          </article>
        ))}
      </div>
    </section>
  )
}

function MachinesPage() {
  return (
    <section>
      <PageHeader title="机器" text="机器按阶段解锁。早期解决能源和加工，中期进入自动化，后期进入化工和核能。" />
      <InfoTable columns={['机器', '阶段', '作用']} rows={machines} />
    </section>
  )
}

function PipesPage() {
  return (
    <section>
      <PageHeader title="管道" text="管道分为物品、能量、流体和控制节点。不同管道服务不同系统，不混在一起。" />
      <InfoTable columns={['管道', '类型', '作用']} rows={pipes} />
    </section>
  )
}

function GuidePage() {
  return (
    <section>
      <PageHeader title="游玩路线" text="这里给玩家一个清晰路线，避免打开模组后不知道下一步做什么。" />
      <div className="timeline">
        {docs.map(([title, text], index) => (
          <article className="timeline-item" key={title}>
            <span>{index + 1}</span>
            <div>
              <h3>{title}</h3>
              <p>{text}</p>
            </div>
          </article>
        ))}
      </div>
    </section>
  )
}

function App() {
  return (
    <div className="wiki-shell">
      <aside className="sidebar">
        <div className="brand">
          <Texture src="/textures/block/core_workbench_front.png" name="核心工作台" />
          <div>
            <strong>核心科技</strong>
            <span>模组百科</span>
          </div>
        </div>
        <nav aria-label="百科目录">
          {navItems.map(([to, label]) => (
            <NavLink className={({ isActive }) => (isActive ? 'active' : undefined)} end={to === '/'} key={to} to={to}>
              {label}
            </NavLink>
          ))}
        </nav>
      </aside>

      <main className="content">
        <Routes>
          <Route path="/" element={<OverviewPage />} />
          <Route path="/blocks" element={<BlocksPage />} />
          <Route path="/minerals" element={<MineralsPage />} />
          <Route path="/vanilla-ores" element={<VanillaOresPage />} />
          <Route path="/parts" element={<PartsPage />} />
          <Route path="/materials" element={<Navigate to="/parts" replace />} />
          <Route path="/machines" element={<MachinesPage />} />
          <Route path="/pipes" element={<PipesPage />} />
          <Route path="/guide" element={<GuidePage />} />
          <Route path="/docs" element={<Navigate to="/guide" replace />} />
          <Route path="*" element={<Navigate to="/" replace />} />
        </Routes>
      </main>
    </div>
  )
}

export default App
