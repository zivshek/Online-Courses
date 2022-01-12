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

fun g f1 f2 p =
    let 
	val r = g f1 f2 
    in
	case p of
	    Wildcard          => f1 ()
	  | Variable x        => f2 x
	  | TupleP ps         => List.foldl (fn (p,i) => (r p) + i) 0 ps
	  | ConstructorP(_,p) => r p
	  | _                 => 0
    end

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
	
