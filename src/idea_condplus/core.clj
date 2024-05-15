(ns idea-condplus.core)

(defmacro cond+ [& clauses]
  (when-some [[test expr & rest] clauses]
    (case test
      :let `(let ~expr (cond+ ~@rest))
      :do  `(do ~expr (cond+ ~@rest))
      :when-let `(when-let ~expr (cond+ ~@rest))
      `(if ~test ~expr (cond+ ~@rest)))))



(defn foo
  []
  (cond+
    :let [a 1
          b 2]
    :when-let [c 3]
    :true a))
