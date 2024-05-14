---
title: jQAssistant pub Plugin
---

# jQAssistant pub Plugin

This is the [pub](https://dart.dev/guides/packages) Plugin for [jQAssistant](https://jqassistant.org).  
It provides a scanner for `pubspec.yaml` files of Dart- or Flutter-based projects.

## Installation

Add the plugin to the `plugins` section of the`jqassistant.yml`configuration file:

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

## Node Types

- [[Node - Pub Environment|:Pub:Environment]]
