# kibit-namespace-aliased-keywords

This repo contains a minimal reproduction of an issue with recent (2017-10) `kibit` functionality in relation to namespace-aliased aliased keywords.

Reproduction uses `[lein-kibit "0.1.6-beta2"]`.

I encountered this issue whilst working with `clojure.spec` where I rely heavily on namespace aliased keywords for concision.

## Issue

Kibit correctly identifies my redundant usage of the threading operator `->` and suggests a replacement that uses keywords as an accessor.

Running `lein kibit` makes appropriate suggestions that would *not* lead to a regression (albeit with the loss of concision):

```
➜  kibit-namespace-aliased-keywords git:(master) ✗ lein kibit
At src/kibit_namespace_aliased_keywords/core.clj:6:
Consider using:
  (:kibit-namespace-aliased-keywords.spec/something something)
instead of:
  (-> something :kibit-namespace-aliased-keywords.spec/something)
```

However running `lein kibit --replace --interactive` and accepting the changes introduces a regression:

```
➜  kibit-namespace-aliased-keywords git:(master) ✗ lein kibit --replace --interactive
Would you like to replace
  (-> something :spec/something)
 with
  (:spec/something something)
in src/kibit_namespace_aliased_keywords/core.clj:6? [yes/no] yes
```

![regression](https://cdn.pbrd.co/images/GOqqhQs.png)

Executing `(foobar)` with the regression returns `nil` rather than the value `1234` as expected.
