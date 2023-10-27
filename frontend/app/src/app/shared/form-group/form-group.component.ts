import {Component, EventEmitter, Input, Output} from '@angular/core';

@Component({
  selector: 'app-form-group',
  templateUrl: './form-group.component.html',
  styleUrls: ['./form-group.component.css']
})
export class FormGroupComponent<T> {
  @Input()
  public required: boolean = false;

  @Input({required: true})
  public model!: T;

  @Input({required: true})
  public modelName: string;

  @Output()
  public modelChange: EventEmitter<T> = new EventEmitter<T>();

  inputChanged(): void {
    this.modelChange.emit(this.model);
  }
}
