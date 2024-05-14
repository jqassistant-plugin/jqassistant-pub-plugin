---
aliases:
  - :Pub:Package:Yaml:File
---

# `:Pub:Package:Yaml:File` Node

-> represents a `pubspec.yaml` file

## Properties

| Name            | Description                                                                            |
|-----------------|----------------------------------------------------------------------------------------|
| `fileName`      | The file name, relative to the scanned directory.                                      |
| `name`          | The name of the package.                                                               |
| `version`       | The version of the package.                                                            |
| `description`   | The description of the package.                                                        |
| `homepage`      | URL pointing to the website of the package                                             |
| `repository`    | URL pointing to the package’s source code repository                                   |
| `issueTracker`  | URL pointing to the package’s issue tracker site                                       |
| `documentation` | URL pointing to the documentation site of the package if it is different from homepage |
| `publishTo`     | "none" for no publishing or specifying an alternative pub server                       |

## Relations

| Name                      | Target Label(s)                                      | Cardinality | Description                                                                     |
|---------------------------|------------------------------------------------------|-------------|---------------------------------------------------------------------------------|
| `HAS_ENVIRONMENT`         | [[Node - Pub Environment\|:Pub:Environment]]         | 0..*        | versions of Dart (and Flutter) to be used by the package                        |
| `HAS_DEPENDENCY`          | [[Node - Pub Dependency\|:Pub:Dependency]]           | 0..*        | immediate dependencies used by the package                                      |
| `HAS_DEV_DEPENDENCY`      | [[Node - Pub Dependency\|:Pub:Dependency]]           | 0..*        | dev-dependencies used by the package                                            |
| `HAS_DEPENDENCY_OVERRIDE` | [[Node - Pub Dependency\|:Pub:Dependency]]           | 0..*        | overrides for dependencies of the package                                       |
| `HAS_EXECUTABLE`          | [[Node - Pub Executable\|:Pub:Executable]]           | 0..*        | scripts of the package to be run on the command line                            |
| `SUPPORTS_PLATFORM`       | [[Node - Pub Platform\|:Pub:Platform]]               | 0..*        | platforms that are supported by the package                                     |
| `FUNDED_THROUGH`          | [[Node - Pub Funding\|:Pub:Funding]]                 | 0..*        | URLs to provide information about how to help fund the package                  |
| `HAS_FALSE_SECRET`        | [[Node - Pub FalseSecret\|:Pub:FalseSecret]]         | 0..*        | ignored file paths when checking for potential security leaks before publishing |
| `HAS_SCREENSHOT`          | [[Node - Pub Screenshot\|:Pub:Screenshot]]           | 0..*        | screenshots to be shown on the publishing site                                  |
| `HAS_TOPIC`               | [[Node - Pub Topic\|:Pub:Topic]]                     | 0..*        | topic names to categorize the package                                           |
| `HAS_IGNORED_ADVISORY`    | [[Node - Pub IgnoredAdvisory\|:Pub:IgnoredAdvisory]] | 0..*        | suppressed security advisories from dependency resolution                       |
