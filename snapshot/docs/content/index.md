---
title: jQAssistant pub Plugin
---

# jQAssistant pub Plugin

This is the [pub](https://dart.dev/guides/packages) Plugin for [jQAssistant](https://jqassistant.org).  
It provides a scanner for `pubspec.yaml` files of Dart- or Flutter-based projects.

## Installation

Add the plugin to the `plugins` section of the `jqassistant.yml` configuration file:

```yaml
jqassistant:
  plugins:
    # Includes the jQAssistant pub plugin
    - group-id: org.jqassistant.plugin
      artifact-id: jqassistant-pub-plugin
      version: 1.0.0-SNAPSHOT
  scan:
    include:
      files:
        - ${project.basedir}/pubspec.yaml
```

> don't forget to specify the matching file inclusion so that the `pubspec.yaml` is scanned

## Reference Documentation

The plugin provides a scanner which accepts files with the name `pubspec.yaml` and creates the structure as described in
this section, using the items defined in the [Dart Docs](https://dart.dev/tools/pub/pubspec) and having nodes labeled
with [[Node - Pub Package|:Pub:Package:Yaml:File]] as entry point.

**Node-Types:**

- [[Node - Pub Package|:Pub:Package:Yaml:File]]
    - [[Node - Pub Environment|:Pub:Environment]]
    - [[Node - Pub Dependency|:Pub:Dependency]]
        - [[Node - Pub Dependency Hosted|:Pub:Dependency:Hosted]]
        - [[Node - Pub Dependency SDK|:Pub:Dependency:SDK]]
        - [[Node - Pub Dependency Git|:Pub:Dependency:Git]]
        - [[Node - Pub Dependency Path|:Pub:Dependency:Path]]
    - [[Node - Pub Executable|:Pub:Executable]]
    - [[Node - Pub Platform|:Pub:Platform]]
    - [[Node - Pub Funding|:Pub:Funding]]
    - [[Node - Pub FalseSecret|:Pub:FalseSecret]]
    - [[Node - Pub Screenshot|:Pub:Screenshot]]
    - [[Node - Pub Topic|:Pub:Topic]]
    - [[Node - Pub IgnoredAdvisory|:Pub:IgnoredAdvisory]]
