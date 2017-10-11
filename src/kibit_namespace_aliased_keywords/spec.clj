(ns kibit-namespace-aliased-keywords.spec
  (:require [clojure.spec :as s]))

(s/def ::something #(= 1234 %))
