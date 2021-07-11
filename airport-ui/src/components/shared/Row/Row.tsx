import React from 'react';
import { Row as RowBase } from 'antd';
import { Gutter } from 'antd/lib/grid/row';

import 'antd/lib/grid/style/index.less';

interface Props {
    children?: React.ReactNode;
    align?: 'top' | 'middle' | 'bottom';
    gutter?: Gutter | [Gutter, Gutter];
    justify?: 'start' | 'end' | 'space-around' | 'space-between';
    className?: string;
}

const Row = ({ children, align, gutter, justify, className }: Props) => {
    return (
        <RowBase align={align} gutter={gutter} justify={justify} className={className}>
            {children}
        </RowBase>
    );
};

export default Row;
