(* Coursera Programming Languages, Homework 3, Provided Code *)

exception NoAnswer

datatype pattern = Wildcard
		 | Variable of string
		 | UnitP
		 | ConstP of int
		 | TupleP of pattern list
		 | ConstructorP of string * pattern

datatype valu = Const of int
	      | Unit
	      | Tuple of valu list
	      | Constructor of string * valu

(**** for the challenge problem only ****)

datatype typ = Anything
	     | UnitT
	     | IntT
	     | TupleT of typ list
	     | Datatype of string

(**** you can put all your code here ****)

(* 1 *)
fun only_capitals xs = List.filter (fn s => Char.isUpper(String.sub(s, 0))) xs

(* 2 *)
fun longest_string1 xs = List.foldl (fn (x, y) =>
					if String.size(x) > String.size(y)
					then x		 
					else y) "" xs

(* 3 *)
fun longest_string2 xs = List.foldl (fn (x, y) =>
					if String.size(x) <= String.size(y)
					then x		 
					else y) "" xs

(* 4 *)
(* Wouldn't it be easier to just have a function that takes two strings and return the longest? *)
fun longest_string_helper f xs = List.foldl (fn (x, y) => if f(String.size x, String.size y)
							  then x
							  else y) "" xs
								 
val longest_string3 = longest_string_helper (fn (x, y) => x > y)
val longest_string4 = longest_string_helper (fn (x, y) => x >= y)

(*5*)
val longest_capitalized = longest_string1 o only_capitals

(*6*)
fun rev_string s = (String.implode o List.rev o String.explode) s

(*7*)
fun first_answer f xs =
    case xs of
	[] => raise NoAnswer
      | x::xs' => case f x of
		      SOME v => v
		   | NONE => first_answer f xs'

(*8*)
fun all_answers f xs =
    let
	fun aux (xs, acc) =
	    case xs of
		[] => SOME acc
	      | x::xs' => case f x of
			      SOME lst => aux(xs', lst @ acc)
			    | NONE => NONE
    in
	aux (xs, [])
    end

(* Moving here for easier referencing *)
fun g f1 f2 p =
    let 
	val r = g f1 f2 
    in
	case p of
	    Wildcard          => f1 () (* did some research, () actually means uint... wth? *)
	  | Variable x        => f2 x
	  | TupleP ps         => List.foldl (fn (p,i) => (r p) + i) 0 ps
	  | ConstructorP(_,p) => r p
	  | _                 => 0
    end
	
(*9a*)
fun count_wildcards p = g (fn u => 1) (fn s => 0) p

(*9b*)
fun count_wild_and_variable_lengths p =
    g (fn u => 1) (fn s => String.size s) p

(*9c*)
fun count_some_var (s, p) =
    g (fn u => 0) (fn s2 => if s2 = s then 1 else 0) p
    
(*10*)
fun check_pat p =
    let
	fun get_all_variables p =
	    case p of
		Variable x => [x]
	      | TupleP ps => List.foldl (fn (p, i) => (get_all_variables p) @ i) [] ps
	      | ConstructorP(_,p) => get_all_variables p
	      | _ => []

	fun has_repeats xs =
	    case xs of
		[] => false
	      | x::xs' => if List.exists (fn s => s = x) xs'
			  then true
			  else has_repeats xs'
    in
	Bool.not(has_repeats(get_all_variables p))
    end

(*11*)
fun match (v, p) =
    case p of
	Wildcard => SOME []
      | Variable s => SOME [(s, v)]
      | UnitP => case v of
		     Unit => SOME []
		   | _ => NONE
      | ConstP cp => case v of
			 Const cv => if cv = cp then SOME [] else NONE
		       | _ => NONE
(*      | TupleP ps => case v of*)
					  
										   
