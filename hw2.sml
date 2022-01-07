(* Dan Grossman, Coursera PL, HW2 Provided Code *)

(* if you use this function to compare two strings (returns true if the same
   string), then you avoid several of the functions in problem 1 having
   polymorphic types that may be confusing *)
fun same_string(s1 : string, s2 : string) =
    s1 = s2

(* put your solutions for problem 1 here *)
	     (* 1.a *)
fun all_except_option (s, ss) =
    case ss of
	[] => NONE
      | x::ss' => if same_string(s, x)
		  then SOME ss'
		  else
		      case all_except_option(s, ss') of
			  NONE => NONE
			| SOME ss' => SOME(x::ss')					   

(* 1.b *)
fun get_substitutions1 (slistlist, s) =
    case slistlist of
	[] => []
      | firstList::restList => case all_except_option(s, firstList) of
				   NONE => get_substitutions1(restList, s)
				 | SOME ss => ss @ get_substitutions1(restList, s)

(* 1.c *)
fun get_substitutions2 (slistlist, s) =
    let
	fun aux (sll, acc) =
	    case sll of
		[] => acc
	      | firstList::restList => case all_except_option(s, firstList) of
					   NONE => aux(restList, acc)
					 | SOME ss => aux(restList, acc @ ss)
    in
	aux(slistlist, [])
    end

(* 1.d *)
fun similar_names (sll, fullname) =
    let
	fun aux (ss, l, m, acc) =
	    case ss of
		[] => acc
	      | x::xs' => aux(xs', l, m, acc @ [{first = x, last = l, middle = m}])
    in
	(* omg, finally got this pattern matching correct... even though passing last and middle looks very stupid... *)
	case fullname of
	    {first, last, middle} => aux(get_substitutions2(sll, first), last, middle, [fullname])
    end
	
								      
(* you may assume that Num is always used with values 2, 3, ..., 10
   though it will not really come up *)
datatype suit = Clubs | Diamonds | Hearts | Spades
datatype rank = Jack | Queen | King | Ace | Num of int 
type card = suit * rank

datatype color = Red | Black
datatype move = Discard of card | Draw 

exception IllegalMove

(* put your solutions for problem 2 here *)
