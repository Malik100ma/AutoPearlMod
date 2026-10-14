# AutoPearl

**Minecraft 1.21.11 · Fabric · client-side**

🇬🇧 English · 🇷🇺 Русский · 🇺🇦 Українська

---

## 🇬🇧 English

Throw an ender pearl with a single key press.

AutoPearl scans **only your hotbar** for an ender pearl. When you press the bind it switches to the pearl's slot, right-clicks (throws it) and immediately returns to the slot you were holding. If there is no pearl in the hotbar, nothing happens.

### Features
- One key = throw a pearl and go back to your previous slot
- Hotbar only (slots 1–9)
- Settings screen on **`[`** (this is the **`х`** key on a Russian layout – same physical key), rebindable in *Options → Controls → AutoPearl*
- In the settings screen click the button, then press any key (or mouse button) – it becomes your pearl bind
- [Mod Menu](https://modrinth.com/mod/modmenu) support (config button)
- Languages: English, Русский, Українська

### Usage
1. Put ender pearls in your hotbar.
2. Press **`R`** (default) – the pearl is thrown.
3. Press **`[`** / **`х`** to open settings and choose another key for the pearl.

### Requirements
- Minecraft 1.21.11, Java 21
- Fabric Loader ≥ 0.18.1
- Fabric API
- Mod Menu (optional)

---

## 🇷🇺 Русский

Бросок эндер-жемчуга одной клавишей.

AutoPearl ищет эндер-жемчуг **только в хотбаре**. По нажатию бинда мод берёт слот с жемчугом, нажимает ПКМ (бросает) и сразу возвращается на слот, на котором вы были. Если жемчуга в хотбаре нет – ничего не происходит.

### Возможности
- Одна клавиша = бросок жемчуга и возврат на прежний слот
- Поиск только в хотбаре (слоты 1–9)
- Настройки открываются клавишей **`[`** (в русской раскладке это **`х`** – та же физическая клавиша), бинд меняется в *Настройки → Управление → AutoPearl*
- В настройках нажимаете на кнопку, затем любую клавишу (или кнопку мыши) – она становится биндом жемчуга
- Поддержка [Mod Menu](https://modrinth.com/mod/modmenu)
- Языки: English, Русский, Українська

### Использование
1. Положите эндер-жемчуг в хотбар.
2. Нажмите **`R`** (по умолчанию) – жемчуг брошен.
3. Нажмите **`[`** / **`х`**, чтобы открыть настройки и выбрать другую клавишу.

### Требования
- Minecraft 1.21.11, Java 21
- Fabric Loader ≥ 0.18.1
- Fabric API
- Mod Menu (необязательно)

---

## 🇺🇦 Українська

Кидок перлини Енду однією клавішею.

AutoPearl шукає перлину Енду **лише в хотбарі**. За натисканням бінда мод бере слот з перлиною, натискає ПКМ (кидає) і одразу повертається на слот, на якому ви були. Якщо перлини в хотбарі немає – нічого не відбувається.

### Можливості
- Одна клавіша = кидок перлини та повернення на попередній слот
- Пошук лише в хотбарі (слоти 1–9)
- Налаштування відкриваються клавішею **`[`** (в українській/російській розкладці це **`х`** – та сама фізична клавіша), бінд змінюється в *Налаштування → Керування → AutoPearl*
- У налаштуваннях натискаєте на кнопку, потім будь-яку клавішу (або кнопку миші) – вона стає біндом перлини
- Підтримка [Mod Menu](https://modrinth.com/mod/modmenu)
- Мови: English, Русский, Українська

### Використання
1. Покладіть перлини Енду в хотбар.
2. Натисніть **`R`** (за замовчуванням) – перлину кинуто.
3. Натисніть **`[`** / **`х`**, щоб відкрити налаштування та обрати іншу клавішу.

### Вимоги
- Minecraft 1.21.11, Java 21
- Fabric Loader ≥ 0.18.1
- Fabric API
- Mod Menu (необов'язково)

---

## 🛠 Building from source / Сборка / Збірка

```
./gradlew build          # Linux / macOS
gradlew.bat build        # Windows
```

The jar appears in `build/libs/` (use the one **without** `-sources`).
Jar лежит в `build/libs/` (берите файл **без** `-sources`).

> The Gradle wrapper jar is not included. Generate it once with `gradle wrapper --gradle-version 9.2.0`,
> or copy `gradlew`, `gradlew.bat` and `gradle/wrapper/gradle-wrapper.jar` from the template at <https://fabricmc.net/develop/template/>.
> Or simply open the folder in IntelliJ IDEA and let it import the Gradle project.

Dev client: `./gradlew runClient`

## License
MIT
