# Word Generator

Word Generator is a Java-based tool designed to generate massive word lists (up to millions or billions of combinations)
using flexible, template-driven rules. It excels at creating custom datasets for security research, performance testing,
and linguistic simulations.

## 1. Introduction

Unlike static list generators, `WG` allows you to define complex relationships between base words, separators, and
suffixes. It is particularly useful for generating words that mimic human behavior, such as typing a word in the wrong
keyboard layout or using specific capitalization patterns.

### What can it do?

* **Template-Driven:** Build words using tokens like `$core[0]` and `$s`.
* **Dual Language Support:** Native support for English (EN) and Russian (RU) alphabets.
* **Keyboard Layout Conversion:** Automatically converts words between layouts (e.g., `привет` -> `ghbdtn`).
* **Recursive Suffixes:** Generate combinations of characters, digits, and symbols up to a specified length.
* **Massive Scale:** Handles file splitting and large-scale generation (hundreds GBs of data) without running out of
  memory.
* **Calculation Mode:** Instantly calculate how many words your configuration *would* generate before you run it.

---

## 2. Getting Started

### Installation

1. **Clone the Repository:**
   ```bash
   git clone https://github.com/your-username/word-generator.git
   ```
    ```bash
   cd word-generator
   ```
2. **Build with Maven:**
   ```bash
   mvn clean package
   ```

### How to Run

Run the executable JAR with the following syntax:

```bash
java -jar target/word-generator-1.0.0.jar <config_path> <output_folder> <max_file_size_gb> <calculation_mode> <debug_ram>
```

| Argument           | Example                 | Description                                          |
|:-------------------|:------------------------|:-----------------------------------------------------|
| `config_path`      | `configs/my_rules.json` | Path to your JSON configuration.                     |
| `output_folder`    | `output/`               | Directory where word lists will be saved.            |
| `max_file_size_gb` | `10`                    | Size limit (in GB) before splitting into a new file. |
| `calculation_mode` | `false`                 | `true` = Only count words; `false` = Save to files.  |
| `debug_ram`        | `false`                 | `true` = Log memory usage for debugging.             |

---

## 3. Configuration Guide

The logic of your word generation is stored in a JSON file.

### Basic Config Example (`configs/simple.json`)

```json
{
    "name": "basic-gen",
    "coreEn": [
        [
            "admin"
        ],
        [
            "pass"
        ]
    ],
    "coreSplitSymbols": [
        "_",
        "."
    ],
    "templates": [
        "$core[0]$s$core[1]"
    ],
    "minSuffixLength": 1,
    "maxSuffixLength": 2,
    "suffixSymbols": [
        "!",
        "?"
    ]
}
```

*This config will generate words like `admin_pass!`, `admin.pass??`, etc.*

### How to make your own:

1. **Core Lists (`coreEn`/`coreRu`):** Define sets of base words.
2. **Templates:** Use `$core[index]` to pick from a set and `$s` to pick from `coreSplitSymbols`.
3. **Suffixes:** `minSuffixLength` and `maxSuffixLength` control the length of characters appended to your base words.
4. **Flags:**
    * `generateRuAsEn`: Type RU words on an EN layout.
    * `generateWithCapitalization`: Generates both `word` and `Word`.

---

## 4. Architecture & Performance

The app is built for speed and reliability:

* **Batching:** Suffixes are processed in batches (default: 500k). This prevents the application from running out of RAM (Out Of Memory errors) when generating and holding millions of words in memory before writing them to disk.
* **Validation:** Every word is validated against the target alphabet to prevent junk data.
* **Low Footprint:** Efficiently clears internal sets to maintain a stable memory profile during long runs.

---

## 5. Future Improvements

- [ ] **Multi-threading:** Use Java threads for parallel processing to fully utilize modern CPUs.
- [ ] **Enhanced Configuration:** Allow users to configure parameters like `batchSize` and more complex capitalization rules (e.g., camelCase, ALL CAPS).
- [ ] **Configurable Alphabets:** Allow users to provide their own custom lists of characters for suffix generation via
  JSON.
- [ ] **Easy Execution:** Add a `.sh` startup script and a `Dockerfile` for easier deployment and usage.
- [ ] **Zip/Gzip Support:** Write compressed output files directly to save disk space.
- [ ] **Exclude Lists:** Option to filter out known words or patterns.

---

## License

Distributed under the MIT License.
