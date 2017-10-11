(ns kibit-namespace-aliased-keywords.core
  (:require [kibit-namespace-aliased-keywords.spec :as spec]))

(def something {::spec/something 1234})

(defn foobar [] (:kibit-namespace-aliased-keywords.spec/something something))

(foobar)
