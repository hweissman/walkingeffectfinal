//*******************************************************************
//  MinHeapNode.java       Java Foundations
//
//  Represents a node, and the root of a subtree, in a heap.
//*******************************************************************

package javafoundations;

public class MinHeapNode<T extends Comparable<T>> extends BTNode<T>
{
   MinHeapNode<T> parent;

   //-----------------------------------------------------------------
   //  Creates a new heap node with the specified data.
   //-----------------------------------------------------------------
   public MinHeapNode (T element)
   {
      super(element);
      parent = null;
   }

   //-----------------------------------------------------------------
   //  Returns the parent node of this node.
   //-----------------------------------------------------------------
   public MinHeapNode<T> getParent()
   {
      return parent;
   }

   //-----------------------------------------------------------------
   //  Returns the parent node of this node.
   //-----------------------------------------------------------------
   public void setParent(MinHeapNode<T> parent)
   {
      this.parent = parent;
   }

   //-----------------------------------------------------------------
   //  Returns the node that will be the parent of the new node.
   //-----------------------------------------------------------------
   public MinHeapNode<T> getParentAdd (MinHeapNode<T> last)
   {
      MinHeapNode<T> result = last;

      while ((result.parent != null) && (result.parent.left != result))
         result = result.parent;

      if (result.parent != null)
         if (result.parent.right == null)
            result = result.parent;
         else
         {
            result = (MinHeapNode<T>) result.parent.right;
            while (result.left != null)
               result = (MinHeapNode<T>) result.left;
         }
      else
         while (result.left != null)
            result = (MinHeapNode<T>) result.left;

      return result;
   }

   //-----------------------------------------------------------------
   //  Moves a newly added leaf up the tree as far as appropriate to
   //  reestablish the heap.
   //-----------------------------------------------------------------
   public void heapifyAdd (MinHeapNode<T> last)
   {
      T temp;
      MinHeapNode<T> current = last;

      while ((current.parent != null) &&
         ((current.element).compareTo(current.parent.element) < 0))
      {
         temp = current.element;
         current.element = current.parent.element;
         current.parent.element = temp;
         current = current.parent;
      }
   }

   //-----------------------------------------------------------------
   //  Returns the node that will be the new last node after a remove.
   //-----------------------------------------------------------------
   public MinHeapNode<T> getNewLastNode(MinHeapNode<T> last)
   {
      MinHeapNode<T> result = last;

      while ((result.parent != null) && (result.parent.left == result))
         result = result.parent;

      if (result.parent != null)
         result = (MinHeapNode<T>) result.parent.left;

      while (result.right != null)
         result = (MinHeapNode<T>) result.right;

      return result;
   }

   //-----------------------------------------------------------------
   //  Reorders this heap after removing the root element.
   //-----------------------------------------------------------------
   public void heapifyRemove(MinHeapNode<T> root)
   {
      T temp;
      MinHeapNode<T> current = root;
      MinHeapNode<T> next = smallerChild (current);

      while (next != null && next.element.compareTo(current.element) < 0)
      {
         temp = current.element;
         current.element = next.element;
         next.element = temp;

         current = next;
         next = smallerChild (current);
      }
   }

   //-----------------------------------------------------------------
   //  Returns the smaller of the two children of the specified node.
   //-----------------------------------------------------------------
   public MinHeapNode<T> smallerChild (MinHeapNode<T> node)
   {
      MinHeapNode<T> smaller = null;

      if (node.left == null && node.right == null)
         smaller = null;
      else if (node.left == null)
         smaller = (MinHeapNode<T>)node.right;
      else if (node.right == null)
         smaller = (MinHeapNode<T>)node.left;
      else if (((MinHeapNode<T>)node.left).element.compareTo(((MinHeapNode<T>)node.right).element) < 0)
         smaller = (MinHeapNode<T>)node.left;
      else
         smaller = (MinHeapNode<T>)node.right;

      return smaller;
   }
}
